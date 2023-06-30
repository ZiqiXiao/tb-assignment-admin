<script setup lang="ts">
import {AsnInfoQuery, AsnInfoVO} from "@/api/asn/types";

defineOptions({
	name: "AsnInfo",
	inheritAttrs: false,
});

import {getAsnInfo} from "@/api/asn";
import {asnLangOptions, asnPriceOptions, asnTechCatOptions} from "@/utils/asnOptions";

const queryFormRef = ref(ElForm);

const loading = ref(false);
const ids = ref<number[]>([]);
const total = ref(0);

const queryParams = reactive<AsnInfoQuery>({
	pageNum: 1,
	pageSize: 10,
});

const asnInfoList = ref<AsnInfoVO[]>();

interface CheckedRole {
	id?: number;
	name?: string;
}
let checkedRole: CheckedRole = reactive({});

/**
 * 查询
 */
function handleQuery() {
	loading.value = true;
	getAsnInfo(queryParams)
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

onMounted(() => {
	handleQuery();
});
</script>

<template>
	<div class="app-container">
		<div class="search">
			<el-form ref="queryFormRef" :model="queryParams" :inline="true">
				<div>
					<el-form-item prop="keywords" label="关键字">
						<el-input
								v-model="queryParams.keywords"
								placeholder="任务描述（关键字）"
								clearable
								@keyup.enter="handleQuery"
						/>
					</el-form-item>

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
			<el-table
					ref="dataTableRef"
					v-loading="loading"
					:data="asnInfoList"
			>
				<el-table-column label="任务编号" prop="asnNo" width="120" />
        <el-table-column label="任务描述" prop="asnDesc" min-width="320">
          <template #default="scope">
            <div style="white-space: pre-wrap;" v-html="scope.row.asnDesc.replace('任务描述：', '<strong>任务描述：</strong>').replace('截止日期：', '<br/><strong>截止日期：</strong>')"></div>
          </template>
        </el-table-column>
				<el-table-column label="任务金额" prop="asnPrice" width="150">
          <template #default="scope">
            <span v-if="scope.row.asnPrice == -1">报价</span>
            <span v-else-if="scope.row.asnPrice == -2">详见任务描述</span>
            <span v-else>{{ scope.row.asnPrice }}</span>
          </template>
        </el-table-column>
		  	<el-table-column label="技术分类" prop="asnTechCat" width="150" />
		  	<el-table-column label="编程语言" prop="asnLang" width="150" />
		  	<el-table-column label="咨询时间" prop="consultDt" width="150" />
			</el-table>

			<pagination
					v-if="total > 0"
					v-model:total="total"
					v-model:page="queryParams.pageNum"
					v-model:limit="queryParams.pageSize"
					@pagination="handleQuery"
			/>
		</el-card>
	</div>
</template>
