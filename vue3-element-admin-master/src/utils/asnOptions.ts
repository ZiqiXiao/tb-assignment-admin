export const asnLangOptions = ref<OptionTypeString[]>([
	{label: 'python', value: 'python'},
	{label: 'java', value: 'java'},
	{label: 'js', value: 'js'},
	{label: 'c', value: 'c'},
	{label: 'c++', value: 'c++'},
]);

export const asnTechCatOptions = ref<OptionTypeString[]>([
	{label: '机器学习', value: '机器学习'},
	{label: '深度学习', value: '深度学习'},
	{label: '数据分析', value: '数据分析'},
	{label: '后端', value: '后端'},
	{label: '前端', value: '前端'},
	{label: '小程序', value: '小程序'},
	{label: '系统', value: '系统'},
	{label: '其他', value: '其他'},
]);

export const asnScnCatOptions = ref<OptionTypeString[]>([
	{label: '作业', value: '作业'},
	{label: '其他', value: '其他'},
]);

export const asnPriceOptions = ref<OptionType[]>([
	{label: '0', value: 0},
	{label: '500', value: 500},
	{label: '1000', value: 1000},
	{label: '2000', value: 2000},
	{label: '5000', value: 5000},
	{label: '10000', value: 10000},
]);

export const asnStatusOptions = ref<OptionType[]>([
	{label: '0：未匹配', value: 0},
	{label: '1：未下单', value: 1},
	{label: '2：预付款', value: 2},
	{label: '3：已发货', value: 3},
	{label: '4：已收货', value: 4},
	{label: '5：已交付', value: 5},
	{label: '6：已核对', value: 6},
	{label: '7：已结算', value: 7},
	{label: '8：已退款', value: 8},
	{label: '9：已流失', value: 9},
]);