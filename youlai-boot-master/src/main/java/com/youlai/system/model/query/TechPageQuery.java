package com.youlai.system.model.query;

import com.youlai.system.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description ="老师信息分页查询对象")
@Data
public class TechPageQuery  extends BasePageQuery {
    @Schema(description="关键字(姓名/支付宝账号)")
    private String keywords;

    @Schema(description="老师Id")
    private Long techId;

    @Schema(description="分成比例")
    private Float ratio;
}
