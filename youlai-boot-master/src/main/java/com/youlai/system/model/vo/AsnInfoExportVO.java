package com.youlai.system.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AsnInfoExportVO {
    @ExcelProperty(value = "Id")
    private Long id;

    @ExcelProperty(value = "内部编号")
    private String asnNo; // 内部编号

    @ExcelProperty(value = "订单编号")
    private String orderNo; // 订单编号

    @ExcelProperty(value = "状态")
    private Integer status; // 状态(0:未匹配;1:未下单;2:预付款;3:已发货;4:已收货;5:已核对;6:已结算;7.已退款;8.已流失;)

    @ExcelProperty(value = "任务场景分类")
    private String asnScnCat; // 任务场景分类

    @ExcelProperty(value = "任务技术分类")
    private String asnTechCat; // 任务技术分类

    @ExcelProperty(value = "编程语言")
    private String asnLang; // 编程语言

    @ExcelProperty(value = "任务金额")
    private Float asnPrice; // 任务金额

    @ExcelProperty(value = "平台金额")
    private Float platPortion; // 平台金额

    @ExcelProperty(value = "老师金额")
    private Float techPortion; // 老师金额

    @ExcelProperty(value = "老师姓名")
    private String techName; // 老师姓名

    @ExcelProperty(value = "支付宝")
    private String alipay; // 支付宝

    @ExcelProperty(value = "老师ID")
    private Long techId; // 老师ID

    @ExcelProperty(value = "客服ID")
    private Long cssId; // 客服ID

    @ExcelProperty(value = "客服姓名")
    private String cssName; // 客服姓名

    @ExcelProperty(value = "咨询日期")
    @DateTimeFormat("yyyy-MM-dd")
    private String consultDt; // 咨询日期

    @ExcelProperty(value = "下单日期")
    @DateTimeFormat("yyyy-MM-dd")
    private String orderDt; // 下单日期

    @ExcelProperty(value = "发货日期")
    @DateTimeFormat("yyyy-MM-dd")
    private String shipDt; // 发货日期

    @ExcelProperty(value = "交付日期")
    @DateTimeFormat("yyyy-MM-dd")
    private String deliverDt; // 交付日期

    @ExcelProperty(value = "收货日期")
    @DateTimeFormat("yyyy-MM-dd")
    private String receiveDt; // 收货日期

    @ExcelProperty(value = "应结日期")
    @DateTimeFormat("yyyy-MM-dd")
    private String checkDt; // 应结日期

    @ExcelProperty(value = "结算日期")
    @DateTimeFormat("yyyy-MM-dd")
    private String settlementDt; // 结算日期

    @ExcelProperty(value = "更新日期")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ExcelProperty(value = "创建日期")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
