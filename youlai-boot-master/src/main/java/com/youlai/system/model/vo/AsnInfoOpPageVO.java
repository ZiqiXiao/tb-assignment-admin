package com.youlai.system.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Schema(description="状态(0:未匹配;1:未下单;2:预付款;3:已付款;4:已发货;5:已收货;6:已核对;7:已结算;)")
    private Integer status; // 状态(0:未匹配;1:未下单;2:预付款;3:已付款;4:已发货;5:已收货;6:已核对;7:已结算;)

    @Schema(description="状态时间")
    private Map<String, LocalDateTime> statusTime; // 状态时间

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

    @Schema(description="咨询时间")
    private LocalDate consultDt; // 咨询时间

    @Schema(description="下单时间")
    private LocalDate orderDt; // 下单时间

    @Schema(description="应结日期")
    private LocalDate checkDt; // 应结日期

    @Schema(description="结算日期")
    private LocalDate settlementDt; // 结算日期
}
