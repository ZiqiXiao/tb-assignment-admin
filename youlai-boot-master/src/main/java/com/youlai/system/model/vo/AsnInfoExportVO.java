package com.youlai.system.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AsnInfoExportVO {
    @Schema(description="主键")
    private Long id;

    @Schema(description="内部编号")
    private String asnNo; // 内部编号

    @Schema(description="订单编号")
    private String orderNo; // 订单编号

    @Schema(description="状态(0:未匹配;1:未下单;2:预付款;3:已付款;4:已发货;5:已收货;6:已核对;7:已结算;8.已退款;)")
    private Integer status; // 状态(0:未匹配;1:未下单;2:预付款;3:已付款;4:已发货;5:已收货;6:已核对;7:已结算;8.已退款;)

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

    @Schema(description="老师ID")
    private Long techId; // 老师ID

    @Schema(description="咨询日期")
    @DateTimeFormat("yyyy-MM-dd")
    private LocalDate consultDt; // 咨询日期

    @Schema(description="下单日期")
    @DateTimeFormat("yyyy-MM-dd")
    private LocalDate orderDt; // 下单日期

    @Schema(description="发货日期")
    @DateTimeFormat("yyyy-MM-dd")
    private LocalDate shipDt; // 发货日期

    @Schema(description="交付日期")
    @DateTimeFormat("yyyy-MM-dd")
    private LocalDate deliverDt; // 交付日期

    @Schema(description="收货日期")
    @DateTimeFormat("yyyy-MM-dd")
    private LocalDate receiveDt; // 收货日期

    @Schema(description="应结日期")
    @DateTimeFormat("yyyy-MM-dd")
    private LocalDate checkDt; // 应结日期

    @Schema(description="结算日期")
    @DateTimeFormat("yyyy-MM-dd")
    private LocalDate settlementDt; // 结算日期

    @ExcelProperty(value = "更新时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ExcelProperty(value = "创建时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
