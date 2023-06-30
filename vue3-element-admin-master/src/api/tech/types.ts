/**
 * 用户查询对象类型
 */
export interface TechQuery extends PageQuery {
  keywords?: string; // 关键字：姓名/支付宝账号
  techId?: number; // 老师ID
  ratio?: number; // 老师分成比例
}

export interface TechPageVO {
  techId?: number; // 老师ID
  techName?: string; // 老师姓名
  ratio?: number; // 老师分成比例
  alipay?: string; // 支付宝账号
  entryDt?: Date; // 入职日期
  depDt?: Date; // 离职日期
}

export interface TechForm {
  techId?: number; // 老师ID
  techName?: string; // 老师姓名
  ratio?: number; // 老师分成比例
  alipay?: string; // 支付宝账号
  entryDt?: Date; // 入职时间
  depDt?: Date; // 离职时间
}