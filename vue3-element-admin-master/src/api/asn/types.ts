/**
 * 任务信息展示查询参数
 */
export interface AsnInfoQuery {
	keywords?: string;
	asnTechCat?: string;
	asnLang?: string;
	asnPriceUpper?: number;
	asnPriceLower?: number;
}

/**
 * 任务信息操作查询参数
 */
export interface AsnInfoOpQuery {
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
 * 任务表单对象
 */
export interface AsnForm {
	/**
	 * 内部编号
	 */
	asnNo?: string;
	/**
	 * 订单编号
	 */
	orderNo: string;
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
}
