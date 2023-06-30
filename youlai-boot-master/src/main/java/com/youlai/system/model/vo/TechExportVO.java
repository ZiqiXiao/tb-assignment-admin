package com.youlai.system.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TechExportVO {
    @ExcelProperty(value = "老师ID")
    private Long techId;

    @ExcelProperty(value = "老师姓名")
    private String techName;

    @ExcelProperty(value = "分成比例")
    private Float ratio;

    @ExcelProperty(value = "支付宝账号")
    private String alipay;

    @ExcelProperty(value = "入职日期")
    @DateTimeFormat("yyyy/MM/dd")
    private LocalDate entryDt;

    @ExcelProperty(value = "离职日期")
    @DateTimeFormat("yyyy/MM/dd")
    private LocalDate depDt;
}
