/**
 * 任务信息展示查询参数
 */
export interface AsnInfoQuery extends PageQuery  {
	keywords?: string;
	asnTechCat?: string;
	asnLang?: string;
	asnPriceUpper?: number;
	asnPriceLower?: number;
}

/**
 * 任务信息操作查询参数
 */
export interface AsnInfoOpQuery extends PageQuery  {
	keywords?: string;
	asnNo?: string;
	orderNo?: string;
	status?: number;
	asnTechCat?: string;
	asnLang?: string;
	cssId?: number;
	techId?: number;
	asnPriceUpper?: number;
	asnPriceLower?: number;
	checkDt?: Date;
	consultDt?: Date;
	settlementDt?: Date;
}

/**
 * 任务信息展示视图
 */
export interface AsnInfoVO {
	/**
	 * 内部编号
	 */
	asnNo?: string;
	/**
	 * 任务描述
	 */
	asnDesc?: string;
	/**
	 * 任务金额
	 */
	asnPrice?: number;
	/**
	 * 任务技术分类
	 */
	asnTechCat?: string;
	/**
	 * 编程语言
	 */
	asnLang?: string;
	/**
	 * 咨询时间
	 */
	consultDt?: Date;
}

/**
 * 任务信息展示视图
 */
export interface AsnInfoOpVO {
	/**
	 * 主键Id
	 */
	id?: number;
	/**
	 * 内部编号
	 */
	asnNo?: string;
	/**
	 * 内部编号
	 */
	orderNo?: string;
	/**
	 * 状态
	 */
	status?: number;
	/**
	 * 任务描述
	 */
	asnDesc?: string;
	/**
	 * 任务金额
	 */
	asnPrice?: number;
	/**
	 * 平台部分
	 */
	platPortion?: number;
	/**
	 * 老师部分
	 */
	techPortion?: number;
	/**
	 * 客服Id
	 */
	cssId?: number;
	/**
	 * 客服姓名
	 */
	cssName?: string;
	/**
	 * 老师Id
	 */
	techId?: number;
	/**
	 * 老师姓名
	 */
	techName?: string;
	/**
	 * 任务技术分类
	 */
	asnTechCat?: string;
	/**
	 * 编程语言
	 */
	asnLang?: string;
	/**
	 * 咨询日期
	 */
	consultDt?: Date;
	/**
	 * 下单日期
	 */
	orderDt?: Date;
	/**
	 * 发货日期
	 */
	shipDt?: Date;
	/**
	 * 收货日期
	 */
	receiveDt?: Date;
	/**
	 * 核对日期
	 */
	checkDt?: Date;
	/**
	 * 结算日期
	 */
	settlementDt?: Date;
}

/**
 * 任务表单对象
 */
export interface AsnForm {
	/**
	 * 主键Id
	 */
	id?: number;
	/**
	 * 内部编号
	 */
	asnNo?: string;
	/**
	 * 订单编号
	 */
	orderNo: string;
	/**
	 * 任务描述
	 */
	asnDesc: string;
	/**
	 * 状态
	 */
	status?: number;
	/**
	 * 任务场景分类
	 */
	asnScnCat: string;
	/**
	 * 任务技术分类
	 */
	asnTechCat?: string;
	/*
	*  编程语言
	* */
	asnLang?: string;
	/*
	* 任务金额
	* */
	asnPrice?: number;
	/*
	* 老师金额
	* */
	techPortion?: number;
	/*
	* 平台金额
	* */
	platPortion?: number;
	/*
 	* 客服ID
	* */
	cssId?: number;
	/*
 	* 老师ID
	* */
	techId?: number;
	/*
 	* 咨询日期
	* */
	orderDt?: string;
	/**
	 * 发货日期
	 */
	shipDt?: string;
	/**
	 * 收货日期
	 */
	receiveDt?: string;
	/**
	 * 核对日期
	 */
	checkDt?: string;
	/**
	 * 结算日期
	 */
	settlementDt?: string;
	/**
	 * 咨询日期
	 */
	consultDt?: string;
}
