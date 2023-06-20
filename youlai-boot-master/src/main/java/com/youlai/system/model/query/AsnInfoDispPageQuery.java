package com.youlai.system.model.query;

import com.youlai.system.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description ="任务信息展示分页查询对象")
@Data
public class AsnInfoDispPageQuery extends BasePageQuery {

    @Schema(description="关键字(任务描述)")
    private String keywords;

    @Schema(description="任务技术分类")
    private String asnTechCat;

    @Schema(description="编程语言")
    private String asnLang;

    @Schema(description="任务金额上限")
    private Float asnPriceUpper;

    @Schema(description="任务金额下限")
    private Float asnPriceLower;
}
