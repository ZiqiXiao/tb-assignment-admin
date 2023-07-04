package com.youlai.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.sql.Date;

@Schema(description = "任务信息表单对象")
@Data
public class AsnInfoForm {

    @Schema(description="主键")
    private Long id;

    @Schema(description="内部编号")
    @NotBlank(message = "内部编号不能为空")
    private String asnNo; // 内部编号

    @Schema(description="订单编号")
    private String orderNo; // 订单编号

    @Schema(description="状态(0:未匹配;1:未下单;2:预付款;3:已发货;4:已收货;5:已核对;6:已结算;7.已退款;8.已流失;)")
    @NotNull(message = "状态不能为空")
    private Integer status; // 状态(0:未匹配;1:未下单;2:预付款;3:已付款;4:已发货;5:已收货;6:已核对;7:已结算;8.已退款;)

    @Schema(description="任务场景分类")
    @NotBlank(message = "任务场景分类不能为空")
    private String asnScnCat; // 任务场景分类

    @Schema(description="任务技术分类")
    @NotBlank(message = "任务技术分类不能为空")
    private String asnTechCat; // 任务技术分类

    @Schema(description="编程语言")
    @NotBlank(message = "编程语言不能为空")
    private String asnLang; // 编程语言

    @Schema(description="任务金额")
    private Float asnPrice; // 任务金额

    @Schema(description="老师金额")
    private Float techPortion; // 老师金额

    @Schema(description="平台金额")
    private Float platPortion; // 平台金额

    @Schema(description="任务描述")
    @NotBlank(message = "任务描述不能为空")
    private String asnDesc; // 任务描述

    @Schema(description="客服ID")
    private Long cssId; // 客服ID

    @Schema(description="老师ID")
    private Long techId; // 老师ID

    @Schema(description="咨询日期")
    @NotNull(message = "咨询日期不能为空")
    private Date consultDt; // 咨询日期

    @Schema(description="下单日期")
    private Date orderDt; // 下单日期

    @Schema(description="发货日期")
    private Date shipDt; // 发货日期

    @Schema(description="交付日期")
    private Date deliverDt; // 交付日期

    @Schema(description="收货日期")
    private Date receiveDt; // 收货日期

    @Schema(description="应结日期")
    private Date checkDt; // 应结日期

    @Schema(description="结算日期")
    private Date settlementDt; // 结算日期
}
