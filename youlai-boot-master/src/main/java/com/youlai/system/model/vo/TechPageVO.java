package com.youlai.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Schema(description ="老师信息分页对象")
@Data
public class TechPageVO {
    @Schema(description="老师Id")
    private Long techId;

    @Schema(description="姓名")
    private String techName;

    @Schema(description="分成比例")
    private Float ratio;

    @Schema(description="支付宝账号")
    private String alipay;

    @Schema(description="入职日期")
    private Date entryDt;

    @Schema(description="离职日期")
    private Date depDt;
}
