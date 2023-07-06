package com.youlai.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Schema(description = "老师表单对象")
@Data
public class TechForm {
    @Schema(description="老师ID")
    @NotNull(message = "老师ID不能为空")
    private Long techId; // 老师Id

    @Schema(description="姓名")
    @NotBlank(message = "姓名不能为空")
    private String techName; // 姓名

    @Schema(description="分成比例")
    @NotNull(message = "分成比例不能为空")
    private Float ratio; // 分成比例

    @Schema(description="支付宝账号")
    private String alipay; // 支付宝账号

    @Schema(description="入职日期")
    @NotNull(message = "入职日期不能为空")
    private Date entryDt; // 入职日期

    @Schema(description="离职日期")
    private Date depDt; // 离职日期
}
