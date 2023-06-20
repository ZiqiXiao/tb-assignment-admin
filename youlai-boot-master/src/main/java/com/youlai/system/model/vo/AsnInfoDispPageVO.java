package com.youlai.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description ="任务信息分页对象")
@Data
public class AsnInfoDispPageVO {
    @Schema(description="内部编号")
    private String asnNo;

    @Schema(description="任务描述")
    private String asnDesc;

    @Schema(description="任务金额")
    private Float asnPrice;

    @Schema(description="任务技术分类")
    private String asnTechCat;

    @Schema(description="编程语言")
    private String asnLang;

    @Schema(description="咨询日期")
    private String consultDt;
}
