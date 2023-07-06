package com.youlai.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.system.common.base.BaseEntity;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("bus_asn_info")
public class BusAsnInfo extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id; // 主键
    private String asnNo; // 内部编号
    private String orderNo; // 订单编号
    private Integer status; // 状态(0:未匹配;1:未下单;2:预付款;3:已发货;4:已收货;5:已核对;6:已结算;7.已退款;8.已流失;)
    private String asnScnCat; // 任务场景分类
    private String asnTechCat; // 任务技术分类
    private String asnLang; // 编程语言
    private Float asnPrice; // 任务金额
    private Float techPortion; // 老师金额
    private Float platPortion; // 平台金额
    private String asnDesc; // 任务描述
    private Long cssId; // 客服ID
    private Long techId; // 老师ID
    private Date consultDt; // 咨询日期
    private Date orderDt; // 下单日期
    private Date shipDt; // 发货日期
    private Date deliverDt; // 交付日期
    private Date receiveDt; // 收货日期
    private Date checkDt; // 应结日期
    private Date settlementDt; // 结算日期

}
