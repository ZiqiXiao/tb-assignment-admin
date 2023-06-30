<script setup lang="ts">/**
 * @see {@link https://vuejs.org/api/sfc-script-setup.html#defineoptions}
 */
import {TechForm, TechPageVO, TechQuery} from "@/api/tech/types";

defineOptions({
  name: "Tech",
  inheritAttrs: false,
});
import { UploadFile } from "element-plus";
import {
  deleteUsers,
  updateUserStatus,
} from "@/api/user";
import { listDeptOptions } from "@/api/dept";
import { listRoleOptions } from "@/api/role";

import { UserForm, UserQuery, UserPageVO } from "@/api/user/types";
import {
  addTech,
  exportTech,
  getMaxTechId,
  getTechForm,
  getTechPage,
  importTech,
  updateTech,
  downloadTemplateApi,
  deleteTechs
} from "@/api/tech";

// const deptTreeRef = ref(ElTree); // 部门树
const queryFormRef = ref(ElForm); // 查询表单
const techFormRef = ref(ElForm); // 老师表单

const loading = ref(false);
const ids = ref([]);
const total = ref(0);
const dialog = reactive<DialogOption>({
  visible: false,
});

const queryParams = reactive<TechQuery>({
  pageNum: 1,
  pageSize: 10,
});
const techList = ref<TechPageVO[]>();

const formData = reactive<TechForm>({
  techId: null,
  techName: "",
  ratio: null,
  alipay: "",
  entryDt: "",
  depDt: ""
});

const rules = reactive({
  techId: [{ required: true, message: "老师Id不能为空", trigger: "blur" }],
  techName: [{ required: true, message: "老师姓名不能为空", trigger: "blur" }],
  ratio: [{ required: true, message: "老师分成比列不能为空", trigger: "blur" }],
  entryDt: [{ required: true, message: "入职日期不能为空", trigger: "blur" }],
});

const deptList = ref<OptionType[]>();
const roleList = ref<OptionType[]>();
const importDialog = reactive<DialogOption>({
  title: "用户导入",
  visible: false,
});
const importDeptId = ref<number>(0);
const excelFile = ref<File>();
const excelFilelist = ref<File[]>([]);

// watchEffect(
//   () => {
//     deptTreeRef.value.filter(searchDeptName.value);
//   },
//   {
//     flush: "post", // watchEffect会在DOM挂载或者更新之前就会触发，此属性控制在DOM元素更新后运行
//   }
// );

/**
 * 部门筛选
 */
function handleDeptFilter(value: string, data: any) {
  if (!value) {
    return true;
  }
  return data.label.indexOf(value) !== -1;
}

/**
 * 部门树节点
 */
function handleDeptNodeClick(data: { [key: string]: any }) {
  queryParams.deptId = data.value;
  handleQuery();
}

/**
 * 获取角色下拉列表
 */
async function getRoleOptions() {
  listRoleOptions().then((response) => {
    roleList.value = response.data;
  });
}

/**
 * 修改用户状态
 */
function handleStatusChange(row: { [key: string]: any }) {
  const text = row.status === 1 ? "启用" : "停用";
  ElMessageBox.confirm("确认要" + text + row.username + "用户吗?", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      return updateUserStatus(row.id, row.status);
    })
    .then(() => {
      ElMessage.success(text + "成功");
    })
    .catch(() => {
      row.status = row.status === 1 ? 0 : 1;
    });
}

/**
 * 查询
 */
function handleQuery() {
  loading.value = true;
  getTechPage(queryParams)
    .then(({ data }) => {
      techList.value = data.list;
      total.value = data.total;
    })
    .finally(() => {
      loading.value = false;
    });
}

/**
 * 重置查询
 */
function resetQuery() {
  queryFormRef.value.resetFields();
  queryParams.pageNum = 1;
  handleQuery();
}

/**
 * 行checkbox change事件
 */
function handleSelectionChange(selection: any) {
  ids.value = selection.map((item: any) => item.techId);
}


/**
 * 打开弹窗
 *
 * @param techId 用户ID
 */
async function openDialog(techId?: number) {
  dialog.visible = true;
  if (techId) {
    dialog.title = "修改老师";
    getTechForm(techId).then(({ data }) => {
      Object.assign(formData, data);
    });
  } else {
    dialog.title = "新增老师";
    getMaxTechId().then(({ data }) => {
      formData.techId = data + 1
    })

  }
}

/**
 * 关闭用户弹窗
 */
function closeDialog() {
  dialog.visible = false;
  resetForm();
}

/**
 * 重置表单
 */
function resetForm() {
  techFormRef.value.resetFields();
  techFormRef.value.clearValidate();

  formData.techId = undefined;
}

/**
 * 表单提交
 */
const handleSubmit = useThrottleFn(() => {
  techFormRef.value.validate((valid: any) => {
    if (valid) {
      const techId = formData.techId;
      loading.value = true;
      if (techId) {
        updateTech(techId, formData)
          .then(() => {
            ElMessage.success("修改老师成功");
            closeDialog();
            resetQuery();
          })
          .finally(() => (loading.value = false));
      } else {
        addTech(formData)
          .then(() => {
            ElMessage.success("新增老师成功");
            closeDialog();
            resetQuery();
          })
          .finally(() => (loading.value = false));
      }
    }
  });
}, 3000);

/**
 * 删除老师
 */
function handleDelete(techId?: number) {
  const techIds = [techId || ids.value].join(",");
  console.log(techId)
  console.log(ids.value)
  console.log(techIds)
  if (!techIds) {
    ElMessage.warning("请勾选删除项");
    return;
  }

  ElMessageBox.confirm("确认删除老师?", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(function () {
    deleteTechs(techIds).then(() => {
      ElMessage.success("删除成功");
      resetQuery();
    });
  });
}

/**
 * 获取部门下拉项
 */
async function getDeptOptions() {
  listDeptOptions().then((response) => {
    deptList.value = response.data;
  });
}

/**
 * 下载导入模板
 */
function downloadTemplate() {
  downloadTemplateApi().then((response: any) => {
    const blob = new Blob([response.data], {
      type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
    });
    const a = document.createElement("a");
    const href = window.URL.createObjectURL(blob); // 下载链接
    a.href = href;
    a.download = decodeURI(
      response.headers["content-disposition"].split(";")[1].split("=")[1]
    ); // 获取后台设置的文件名称
    document.body.appendChild(a);
    a.click(); // 点击下载
    document.body.removeChild(a); // 下载完成移除元素
    window.URL.revokeObjectURL(href); // 释放掉blob对象
  });
}

/**
 * 导入弹窗
 */
async function openImportDialog() {
  await getDeptOptions();
  await getRoleOptions();
  importDialog.visible = true;
}

/**
 * Excel文件change事件
 *
 * @param file
 */
function handleExcelChange(file: UploadFile) {
  if (!/\.(xlsx|xls|XLSX|XLS)$/.test(file.name)) {
    ElMessage.warning("上传Excel只能为xlsx、xls格式");
    excelFile.value = undefined;
    excelFilelist.value = [];
    return false;
  }
  excelFile.value = file.raw;
}

/**
 * 导入用户
 */
function handleTechImport() {
  if (!excelFile.value) {
    ElMessage.warning("上传Excel文件不能为空");
    return false;
  }
  importTech(excelFile.value).then((response) => {
    ElMessage.success(response.data);
    closeImportDialog();
    resetQuery();
  });
}

/**
 * 关闭导入弹窗
 */
function closeImportDialog() {
  importDialog.visible = false;
  excelFile.value = undefined;
  excelFilelist.value = [];
}

/**
 * 导出用户
 */
function handleUserExport() {
  exportTech(queryParams).then((response: any) => {
    const blob = new Blob([response.data], {
      type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
    });
    const a = document.createElement("a");
    const href = window.URL.createObjectURL(blob); // 下载的链接
    a.href = href;
    a.download = decodeURI(
      response.headers["content-disposition"].split(";")[1].split("=")[1]
    ); // 获取后台设置的文件名称
    document.body.appendChild(a);
    a.click(); // 点击导出
    document.body.removeChild(a); // 下载完成移除元素
    window.URL.revokeObjectURL(href); // 释放掉blob对象
  });
}

onMounted(() => {
  getDeptOptions(); // 初始化部门
  handleQuery(); // 初始化用户列表数据
});
</script>

<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :lg="20" :xs="24">
        <div class="search">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="关键字" prop="keywords">
              <el-input
                v-model="queryParams.keywords"
                placeholder="名字/支付宝账号/Id"
                clearable
                style="width: 200px"
                @keyup.enter="handleQuery"
              />
            </el-form-item>
            <el-form-item label="老师Id" prop="techId">
              <el-input
                  v-model="queryParams.techId"
                  placeholder="老师Id"
                  clearable
                  style="width: 200px"
                  @keyup.enter="handleQuery"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleQuery"
                ><i-ep-search />搜索</el-button
              >
              <el-button @click="resetQuery">
                <i-ep-refresh />
                重置</el-button
              >
            </el-form-item>
          </el-form>
        </div>

        <el-card shadow="never">
          <template #header>
            <div class="flex justify-between">
              <div>
                <el-button
                  type="success"
                  @click="openDialog()"
                  ><i-ep-plus />新增</el-button
                >
                <el-button
                  type="danger"
                  :disabled="ids.length === 0"
                  @click="handleDelete()"
                  ><i-ep-delete />删除</el-button
                >
              </div>
              <div>
                <el-dropdown split-button>
                  导入
                  <template #dropdown>
                    <el-dropdown-menu>
                      <el-dropdown-item @click="downloadTemplate">
                        <i-ep-download />下载模板</el-dropdown-item
                      >
                      <el-dropdown-item @click="openImportDialog">
                        <i-ep-top />导入数据</el-dropdown-item
                      >
                    </el-dropdown-menu>
                  </template>
                </el-dropdown>
                <el-button class="ml-3" @click="handleUserExport"
                  ><template #icon><i-ep-download /></template>导出</el-button
                >
              </div>
            </div>
          </template>

          <el-table
            v-loading="loading"
            :data="techList"
            :default-sort = "{prop: 'techId', order: 'descending'}"
            @selection-change="handleSelectionChange"
          >
            <el-table-column type="selection" width="50" align="center" />
            <el-table-column
              key="techId"
              sortable
              label="老师Id"
              align="center"
              prop="techId"
              width="100"
            />
            <el-table-column
              key="techName"
              label="老师姓名"
              align="center"
              prop="techName"
              width="80"
            />
            <el-table-column
              label="分成比列"
              width="100"
              align="center"
              prop="ratio"
            />

            <el-table-column
              label="支付宝账号"
              align="center"
              prop="alipay"
            />
            <el-table-column label="操作" fixed="right" width="220">
              <template #default="scope">
                <el-button
                  type="primary"
                  link
                  size="small"
                  @click="openDialog(scope.row.techId)"
                  ><i-ep-edit />编辑</el-button
                >
                <el-button
                  type="primary"
                  link
                  size="small"
                  @click="handleDelete(scope.row.techId)"
                  ><i-ep-delete />删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>

          <pagination
            v-if="total > 0"
            v-model:total="total"
            v-model:page="queryParams.pageNum"
            v-model:limit="queryParams.pageSize"
            @pagination="handleQuery"
          />
        </el-card>
      </el-col>
    </el-row>

    <!-- 表单弹窗 -->
    <el-dialog
      v-model="dialog.visible"
      :title="dialog.title"
      width="600px"
      append-to-body
      @close="closeDialog"
    >
      <el-form
        ref="techFormRef"
        :model="formData"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="老师Id" prop="techId">
          <el-input
            v-model="formData.techId"
            :readonly="!!formData.techId"
            placeholder="请输入老师Id"
          />
        </el-form-item>

        <el-form-item label="姓名" prop="techName">
          <el-input v-model="formData.techName" placeholder="请输入姓名" />
        </el-form-item>

        <el-form-item label="分成比例" prop="ratio">
          <el-select v-model="formData.ratio" placeholder="请选择">
            <el-option label="80%" :value="0.8" />
          </el-select>
        </el-form-item>

        <el-form-item label="支付宝账号" prop="alipay">
          <el-input v-model="formData.alipay" placeholder="请输入支付宝账号" />
        </el-form-item>

        <el-form-item label="入职日期" prop="entryDt">
          <el-date-picker type="date" placeholder="选择日期" v-model="formData.entryDt" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="离职日期" prop="depDt">
          <el-date-picker type="date" placeholder="选择日期" v-model="formData.depDt" style="width: 100%;"></el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="handleSubmit">确 定</el-button>
          <el-button @click="closeDialog">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 导入弹窗 -->
    <el-dialog
      v-model="importDialog.visible"
      :title="importDialog.title"
      width="600px"
      append-to-body
      @close="closeImportDialog"
    >
      <el-form label-width="80px">
        <el-form-item label="Excel">
          <el-upload
            class="upload-demo"
            action=""
            drag
            :auto-upload="false"
            accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
            :file-list="excelFilelist"
            :on-change="handleExcelChange"
            :limit="1"
          >
            <el-icon class="el-icon--upload">
              <i-ep-upload-filled />
            </el-icon>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">xls/xlsx files</div>
            </template>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="handleTechImport">确 定</el-button>
          <el-button @click="closeImportDialog">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>
