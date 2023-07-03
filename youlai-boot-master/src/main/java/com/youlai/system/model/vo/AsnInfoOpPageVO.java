package com.youlai.system.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Schema(description ="任务信息分页对象")
@Data
public class AsnInfoOpPageVO {

    @Schema(description="主键ID")
    private Long id; // 主键ID

    @Schema(description="内部编号")
    private String asnNo; // 内部编号

    @Schema(description="订单编号")
    private String orderNo; // 订单编号

    @Schema(description="状态(0:未匹配;1:未下单;2:预付款;3:已发货;4:已收货;5:已核对;6:已结算;7.已退款;8.已流失;)")
    private Integer status; // 状态(0:未匹配;1:未下单;2:预付款;3:已发货;4:已收货;5:已核对;6:已结算;7.已退款;8.已流失;)

    @Schema(description="任务场景分类")
    private String asnScnCat; // 任务场景分类

    @Schema(description="任务技术分类")
    private String asnTechCat; // 任务技术分类

    @Schema(description="编程语言")
    private String asnLang; // 编程语言

    @Schema(description="任务金额")
    private Float asnPrice; // 任务金额

    @Schema(description="老师金额")
    private Float techPortion; // 老师金额

    @Schema(description="平台金额")
    private Float platPortion; // 平台金额

    @Schema(description="任务描述")
    private String asnDesc; // 任务描述

    @Schema(description="客服ID")
    private Long cssId; // 客服ID

    @Schema(description="客服名称")
    private String cssName;

    @Schema(description="老师ID")
    private Long techId; // 老师ID

    @Schema(description="老师姓名")
    private String techName;

    @Schema(description="咨询日期")
    private String consultDt; // 咨询日期

    @Schema(description="下单日期")
    private String orderDt; // 下单日期

    @Schema(description="发货日期")
    private String shipDt; // 发货日期

    @Schema(description="收货日期")
    private String receiveDt; // 收货日期

    @Schema(description="应结日期")
    private String checkDt; // 应结日期

    @Schema(description="结算日期")
    private String settlementDt; // 结算日期
}
