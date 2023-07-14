package com.youlai.system.model.query;

import com.youlai.system.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Schema(description ="任务信息展示分页查询对象")
@Data
public class AsnInfoOpPageQuery extends BasePageQuery {
    @Schema(description="内部编号")
    private String asnNo; // 内部编号

    @Schema(description="订单编号")
    private String orderNo; // 订单编号

    @Schema(description="状态(0:未匹配;1:未下单;2:预付款;3:已发货;4:已收货;5:已核对;6:已结算;7.已退款;8.已流失;)")
    private String status; // 状态(0:未匹配;1:未下单;2:预付款;3:已付款;4:已发货;5:已收货;6:已核对;7:已结算;)

    @Schema(description="状态(0:未匹配;1:未下单;2:预付款;3:已发货;4:已收货;5:已核对;6:已结算;7.已退款;8.已流失;)")
    private List<Integer> statusList; // 状态(0:未匹配;1:未下单;2:预付款;3:已付款;4:已发货;5:已收货;6:已核对;7:已结算;)

    @Schema(description="任务场景分类")
    private String asnScnCat; // 任务场景分类

    @Schema(description="任务技术分类")
    private String asnTechCat; // 任务技术分类

    @Schema(description="编程语言")
    private String asnLang; // 编程语言

    @Schema(description="任务金额上限")
    private Long asnPriceUpper;

    @Schema(description="任务金额下限")
    private Long asnPriceLower;
//
//    @Schema(description="老师金额上限")
//    private Long techPortionUpper;
//
//    @Schema(description="老师金额下限")
//    private Long techPortionLower;
//
//    @Schema(description="平台金额上限")
//    private Long platPortionUpper;
//
//    @Schema(description="平台金额下限")
//    private Long platPortionLower;

    @Schema(description="关键字(任务描述)")
    private String keywords; // 任务描述(关键词)

    @Schema(description="客服ID")
    private Long cssId; // 客服ID
//
//    @Schema(description="客服姓名")
//    private String cssName; // 客服姓名
//
//    @Schema(description="客服代码")
//    private String cssCode; // 客服代码

    @Schema(description="老师ID")
    private Long techId; // 老师ID

    @Schema(description="应结日期")
    private String checkDt; // 应结日期

    @Schema(description="结算日期")
    private String settlementDt; // 结算日期

//    @Schema(description="老师姓名")
//    private String techName; // 老师姓名
//
//    @Schema(description="老师支付宝账号")
//    private String alipay; // 老师支付宝账号
//
//    @Schema(description="分成比例")
//    private Float ratio; // 分成比例

//    @Schema(description="咨询时间")
//    private LocalDate consultDtLower; // 咨询时间下限
//
//    @Schema(description="咨询时间")
//    private LocalDate consultDtUpper; // 咨询时间上限
//
//    @Schema(description="下单时间")
//    private LocalDate orderDtLower; // 下单日期下限
//
//    @Schema(description="下单时间")
//    private LocalDate orderDtUpper; // 下单日期上限
//
//    @Schema(description="发货日期")
//    private LocalDate shipDtLower; // 发货日期下限
//
//    @Schema(description="发货日期")
//    private LocalDate shipDtUpper; // 发货日期上限
//
//    @Schema(description="交付日期")
//    private LocalDate deliverDtLower; // 交付日期下限
//
//    @Schema(description="交付日期")
//    private LocalDate deliverDtUpper; // 交付日期上限
//
//    @Schema(description="收货日期")
//    private LocalDate receiveDtLower; // 收货日期下限
//
//    @Schema(description="收货日期")
//    private LocalDate receiveDtUpper; // 收货日期上限
//
//    @Schema(description="应结日期")
//    private LocalDate checkDtLower; // 应结日期下限
//
//    @Schema(description="应结日期")
//    private LocalDate checkDtUpper; // 应结日期上限
//
//    @Schema(description="结算日期")
//    private LocalDate settlementDtLower; // 结算日期下限
//
//    @Schema(description="结算日期")
//    private LocalDate settlementDtUpper; // 结算日期上限
//
//    @Schema(description="创建时间")
//    private LocalDateTime createTimeLower; // 创建时间下限
//
//    @Schema(description="创建时间")
//    private LocalDateTime createTimeUpper; // 创建时间上限
//
//    @Schema(description="更新时间")
//    private LocalDateTime updateTimeLower; // 更新时间下限
//
//    @Schema(description="更新时间")
//    private LocalDateTime updateTimeUpper; // 更新时间上限
}
