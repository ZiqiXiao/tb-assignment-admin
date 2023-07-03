<script setup lang="ts">
import {getMaxAsnNo} from "@/api/css";

defineOptions({
	name: "AsnInfoOp",
	inheritAttrs: false,
});

import {addAsn, exportAsn, getAsnForm, getAsnInfo, getAsnInfoOp, updateAsn} from "@/api/asn";
import {
	asnLangOptions,
	asnPriceOptions,
	asnScnCatOptions,
	asnStatusOptions,
	asnTechCatOptions
} from "@/utils/asnOptions";
import {useUserStore} from "@/store/modules/user";
import {AsnForm, AsnInfoOpQuery, AsnInfoOpVO} from "@/api/asn/types";

const queryFormRef = ref(ElForm);
const asnInfoFormRef = ref(ElForm);

const loading = ref(false);
const ids = ref<number[]>([]);
const total = ref(0);

const userStore = useUserStore();

const cssCode = userStore.cssCode;
const cssId = userStore.cssId;

const queryParams = reactive<AsnInfoOpQuery>({
	pageNum: 1,
	pageSize: 10,
});

const now = new Date()
const currentDate = `${now.getFullYear()}-${("0" + (now.getMonth() + 1)).slice(-2)}-${("0" + now.getDate()).slice(-2)}`;

const asnInfoList = ref<AsnInfoOpVO[]>();

const dialog = reactive<DialogOption>({
	visible: false,
});

const formData = reactive<AsnForm>({
		asnNo: "",
		orderNo: "",
		status: 0,
		asnLang: "python",
		asnScnCat: "作业",
		asnTechCat: "数据分析",
		asnDesc: "任务描述：\n截止日期：",
		asnPrice: -1,
		cssId: cssId,
		techId: 60000,
    checkDt: undefined,
    settlementDt: undefined,
		consultDt: currentDate
});

const rules = reactive({
	asnNo: [{ required: true, message: "请输内部编号", trigger: "blur" }],
	status: [{ required: true, message: "请选择状态", trigger: "blur" }],
	asnLang: [{ required: true, message: "请输入编程语言", trigger: "blur" }],
  orderNo: [
    {
      validator: (rule, value, callback) => {
        const specialStatusValues = [2, 3, 4, 5, 6, 7];
        if (specialStatusValues.includes(formData.status) && !value) {
          callback(new Error('订单号不能为空'));
        } else {
          callback();
        }
      },
      trigger: 'blur'
    }
  ],
});

/**
 * 查询
 */
function handleQuery() {
	loading.value = true;
  console.log(queryParams)
	getAsnInfoOp(queryParams)
		.then(({ data }) => {
			asnInfoList.value = data.list;
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
 * 重置查询(不重置查询参数)
 */
function dirtyResetQuery() {
  handleQuery();
}

/**
 * 打开角色表单弹窗
 *
 * @param id
 * @param asnNo
 */
function openDialog(id?: number, asnNo?: string) {
  // resetForm();
	dialog.visible = true;
	if (id && !asnNo) {
		dialog.title = "修改任务";
		getAsnForm(id).then(({ data }) => {
			Object.assign(formData, data);
		});
	} else {
    getMaxAsnNo(cssId).then(({ data }) => {
      formData.asnNo = 'tb-' + cssCode + '-' + String(parseInt(data.split('-')[2]) + 1);
    });
		dialog.title = "新增订单";
	}
	// if (id && asnNo) {
	//   dialog.title = "新增关联订单";
	//   getAsnForm(id).then(({ data }) => {
	// 	  Object.assign(formData, data);
	// 	  formData.id = undefined;
	// 		formData.orderNo = '';
	// 		formData.asnPrice = -1;
	//   });
	// }
}

/**
 * 重置表单
 */
function resetForm() {
	asnInfoFormRef.value.resetFields();
	asnInfoFormRef.value.clearValidate();

	formData.asnNo = "";
	formData.orderNo = "";
	formData.status = 0;
	formData.asnLang = "python";
	formData.asnScnCat = "作业";
	formData.asnTechCat = "数据分析";
	formData.asnDesc = "任务描述：\n截止日期：";
	formData.asnPrice = -1;
	formData.cssId = cssId;
	formData.techId = 60000;
	formData.consultDt = currentDate
  formData.checkDt = undefined;
  formData.settlementDt = undefined;
  formData.id = undefined;
}

/**
 * 关闭弹窗
 */
function closeDialog() {
	dialog.visible = false;
	resetForm();
}
/**
 * 任务表单提交
 */
function handleSubmit() {
	loading.value = true;
	asnInfoFormRef.value.validate((valid: any) => {
		if (valid) {
			const id = formData.id;
			if (id) {
				updateAsn(id, formData)
					.then(() => {
						ElMessage.success("修改成功");
						closeDialog();
            dirtyResetQuery();
					})
					.finally(() => (loading.value = false));
			} else {
				addAsn(formData)
					.then(() => {
						ElMessage.success("新增成功");
						closeDialog();
            resetQuery();
					})
					.finally(() => (loading.value = false));
			}
		}
	});
}

/**
 * 导出任务数据
 */
function handleUserExport() {
	exportAsn(queryParams).then((response: any) => {
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

/*
* 生成任务信息到剪贴板
* */
function generateText(row?: any) {
  let budget = undefined;
  if (row.asnPrice === -1) {
    budget = '报价';
  }
  else if (row.asnPrice === -2) {
    budget = '详见任务描述';
  }
  else {
    budget = row.asnPrice;
  }
  // 根据你的需求来生成文字
  return `${row.asnDesc}\n编程语言：${row.asnLang}\n任务预算：${budget}\n内部编号：${row.asnNo}`;
}

/*
* 生成任务信息到剪贴板，成功提示
* */
function handleClipboardSuccess() {
  ElMessageBox.alert("复制成功~请到企业微信粘贴")
}

/*
* 生成任务信息到剪贴板，失败提示
* */
function handleClipboardError() {
  ElMessageBox.alert('复制失败');
}

function getNextTuesday() {
  const nextTuesday = new Date();
  const currentDay = nextTuesday.getDay();
  const daysUntilNextTuesday = (2 - currentDay + 7) % 7 || 7;
  nextTuesday.setDate(nextTuesday.getDate() + daysUntilNextTuesday);
  return now;
}

onMounted(() => {
	handleQuery();
});
</script>

<template>
	<div class="app-container">
		<div class="search">
			<el-form ref="queryFormRef" :model="queryParams" :inline="true">
				<div>
					<el-form-item label="内部编号" prop="asnNo">
						<el-input v-model="queryParams.asnNo" placeholder="内部编号" style="width: 120px"></el-input>
					</el-form-item>

					<el-form-item label="订单编号" prop="orderNo">
						<el-input v-model="queryParams.orderNo" placeholder="订单编号" style="width: 300px"></el-input>
					</el-form-item>

					<el-form-item label="状态" prop="status">
						<el-select
								v-model="queryParams.status"
								placeholder="状态"
								clearable
						>
							<el-option
									v-for="item in asnStatusOptions"
									:key="item.value"
									:label="item.label"
									:value="item.value">
							</el-option>
						</el-select>
					</el-form-item>
				</div>
				<div>
					<el-form-item label="编程语言" prop="asnLang">
						<el-select
								v-model="queryParams.asnLang"
								placeholder="编程语言"
								clearable
						>
							<el-option
									v-for="item in asnLangOptions"
									:key="item.value"
									:label="item.label"
									:value="item.value">
							</el-option>
						</el-select>
					</el-form-item>

					<el-form-item label="技术分类" prop="asnTechCat">
						<el-select
								v-model="queryParams.asnTechCat"
								placeholder="编程语言"
								clearable
						>
							<el-option
									v-for="item in asnTechCatOptions"
									:key="item.value"
									:label="item.label"
									:value="item.value">
							</el-option>
						</el-select>
					</el-form-item>
				</div>
				<div>
					<el-form-item label="价格区间" prop="asnPriceLower">
						<el-select
								v-model="queryParams.asnPriceLower"
								placeholder="最小值"
								clearable
						>
							<el-option
									v-for="item in asnPriceOptions"
									:key="item.value"
									:label="item.label"
									:value="item.value">
							</el-option>
						</el-select>
						<el-select
								v-model="queryParams.asnPriceUpper"
								placeholder="最大值"
								clearable
						>
							<el-option
									v-for="item in asnPriceOptions"
									:key="item.value"
									:label="item.label"
									:value="item.value">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item prop="keywords" label="关键字">
						<el-input
								v-model="queryParams.keywords"
								placeholder="任务描述（关键字）"
								clearable
								@keyup.enter="handleQuery"
						/>
					</el-form-item>
				</div>
        <div>
          <el-form-item prop="cssId" label="客服Id">
            <el-input
                v-model="queryParams.cssId"
                placeholder="客服Id"
                clearable
                @keyup.enter="handleQuery"
            />
          </el-form-item>
          <el-form-item prop="checkDt" label="应结日期">
            <el-date-picker
                v-model="queryParams.checkDt"
                type="date"
                placeholder="请选择日期"
                clearable
                format="YYYY年MM月DD日"
                value-format='YYYY-MM-DD'
                @keyup.enter="handleQuery"
            />
          </el-form-item>
          <el-form-item prop="settlementDt" label="结算日期">
            <el-date-picker
                v-model="queryParams.settlementDt"
                type="date"
                placeholder="请选择日期"
                clearable
                format="YYYY年MM月DD日"
                value-format='YYYY-MM-DD'
                @keyup.enter="handleQuery"
            />
          </el-form-item>
        </div>


				<div>
					<el-form-item>
						<el-button type="primary" @click="handleQuery"
						><i-ep-search />搜索</el-button
						>
						<el-button @click="resetQuery"><i-ep-refresh />重置</el-button>
					</el-form-item>
				</div>

			</el-form>
		</div>

		<el-card shadow="never">
			<template #header>
		  <div class="flex justify-between">
				<div>
					<el-button type="success" @click="openDialog()"
					><i-ep-plus />新增</el-button
					>
				</div>
				<div>
					<el-button class="ml-3" @click="handleUserExport"
					><template #icon><i-ep-download /></template>导出</el-button
					>
				</div>
			</div>
			</template>
			<el-table
					ref="dataTableRef"
		  		v-loading="loading"
					stripe
					:data="asnInfoList"
				 :default-sort = "{prop: 'asnNo', order: 'descending'}"
			>
		  	<el-table-column label="ID" prop="id" width="80" />
				<el-table-column sortable label="任务编号" prop="asnNo" width="120" />
				<el-table-column label="订单编号" prop="orderNo" width="180" />
        <el-table-column label="客服Id" prop="cssId" width="80" />
				<el-table-column label="状态" prop="status" width="100">
          <template #default="scope">
             <span>{{ asnStatusOptions[scope.row.status].label }}</span>
          </template>
        </el-table-column>
		  	<el-table-column label="任务描述" prop="asnDesc" min-width="320">
          <template #default="scope">
            <div style="white-space: pre-wrap;" v-html="scope.row.asnDesc.replace('任务描述：', '<strong>任务描述：</strong>').replace('截止日期：', '<br/><strong>截止日期：</strong>')"></div>
          </template>
        </el-table-column>
				<el-table-column label="任务金额" prop="asnPrice" width="100" >
          <template #default="scope">
            <span v-if="scope.row.asnPrice == -1">报价</span>
            <span v-else-if="scope.row.asnPrice == -2">详见任务描述</span>
            <span v-else>{{ scope.row.asnPrice }}</span>
          </template>
        </el-table-column>
		  	<el-table-column label="平台金额" prop="platPortion" width="100" />
		  	<el-table-column label="老师金额" prop="techPortion" width="100" />
        <el-table-column label="老师Id" prop="techId" width="80" />
        <el-table-column label="老师姓名" prop="techName" width="100" />
		  	<el-table-column label="技术分类" prop="asnTechCat" width="100" />
		  	<el-table-column label="编程语言" prop="asnLang" width="100" />
		  	<el-table-column label="咨询日期" prop="consultDt" width="100" />
        <el-table-column label="下单日期" prop="orderDt" width="100" />
        <el-table-column label="发货日期" prop="shipDt" width="100" />
        <el-table-column label="收货日期" prop="receiveDt" width="100" />
        <el-table-column label="应结日期" prop="checkDt" width="100" />
        <el-table-column label="结算日期" prop="settlementDt" width="100" />
				<el-table-column fixed="right" label="操作" width="150">
					<template #default="scope">
						<el-button
								type="primary"
								size="small"
								link
								@click="openDialog(scope.row.id)"
						>
							<i-ep-edit />编辑
						</el-button>
<!--						<el-button-->
<!--								type="primary"-->
<!--								size="small"-->
<!--								link-->
<!--								@click="openDialog(scope.row.id, scope.row.asnNo)"-->
<!--						>-->
<!--							<i-ep-edit />新增关联-->
<!--						</el-button>-->
            <el-button
                v-clipboard:copy="generateText(scope.row)"
                v-clipboard:success="handleClipboardSuccess"
                v-clipboard:error="handleClipboardError"
                type="primary"
                size="small"
                link
            >
              <i-ep-edit />生成文本
            </el-button>
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

	  <!-- 任务表单弹窗 -->
	  <el-dialog
			  v-model="dialog.visible"
			  :title="dialog.title"
			  width="500px"
			  @close="closeDialog"
	  >
		  <el-form
				  ref="asnInfoFormRef"
				  :model="formData"
				  :rules="rules"
				  label-width="100px"
		  >
			  <el-form-item label="内部编号" prop="asnNo">
				  <el-input v-model="formData.asnNo" placeholder="请输入内部编号">
					</el-input>
			  </el-form-item>

				<el-form-item label="订单编号" prop="orderNo">
					<el-input v-model="formData.orderNo" placeholder="请输入订单编号" />
				</el-form-item>

		  <el-form-item label="状态" prop="status">
				<el-select v-model="formData.status">
					<el-option
							v-for="item in asnStatusOptions"
							:key="item.value"
							:label="item.label"
							:value="item.value">
					</el-option>
				</el-select>
		  </el-form-item>

		  <el-form-item label="编程语言" prop="asnLang">
			  <el-select v-model="formData.asnLang">
					<el-option
							v-for="item in asnLangOptions"
							:key="item.value"
							:label="item.label"
							:value="item.value">
					</el-option>
			  </el-select>
		  </el-form-item>

		  <el-form-item label="场景分类" prop="asnScnCat">
			  <el-select v-model="formData.asnScnCat">
				  <el-option
						  v-for="item in asnScnCatOptions"
						  :key="item.value"
						  :label="item.label"
						  :value="item.value">
				  </el-option>
			  </el-select>
		  </el-form-item>

		  <el-form-item label="技术分类" prop="asnTechCat">
			  <el-select v-model="formData.asnTechCat">
				  <el-option
						  v-for="item in asnTechCatOptions"
						  :key="item.value"
						  :label="item.label"
						  :value="item.value">
				  </el-option>
			  </el-select>
		  </el-form-item>

		  <el-form-item label="任务描述" prop="asnDesc">
				<el-input
						v-model="formData.asnDesc"
						type="textarea"
						:rows="3"
						placeholder="请输入内容">
				</el-input>
		  </el-form-item>

		  <el-form-item label="任务价格" prop="asnPrice">
			  <el-input
					  v-model="formData.asnPrice"
					  type="input"
					  placeholder="请输入价格">
			  </el-input>
		  </el-form-item>

		  <el-form-item label="老师Id" prop="techId">
			  <el-input
					  v-model="formData.techId"
					  type="input"
					  placeholder="请输入老师Id">
			  </el-input>
		  </el-form-item>

		  <el-form-item label="客服Id" prop="cssId">
			  <el-input
					  v-model="formData.cssId"
					  type="input"
					  placeholder="请输入客服Id">
			  </el-input>
		  </el-form-item>

		  <el-form-item label="咨询日期" prop="consultDt">
			  <el-input
					  v-model="formData.consultDt"
					  type="input"
						placeholder="formData.consultDt"
					  :disabled="true">
			  </el-input>
		  </el-form-item>

		  </el-form>

		  <template #footer>
			  <div class="dialog-footer">
				  <el-button type="primary" @click="handleSubmit">确 定</el-button>
				  <el-button @click="closeDialog">取 消</el-button>
			  </div>
		  </template>
	  </el-dialog>
	</div>
</template>
