package com.youlai.system.model.bo;

import lombok.Data;

@Data
public class TechBO {
    private Long techId; // 老师Id
    private String techName; // 姓名
    private Float ratio; // 分成比例
    private String alipay; // 支付宝账号
    private String entryDt; // 入职日期
    private String depDt; // 离职日期
}
