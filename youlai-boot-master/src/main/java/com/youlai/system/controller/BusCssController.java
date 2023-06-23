package com.youlai.system.controller;

import com.youlai.system.common.result.Result;
import com.youlai.system.model.vo.UserInfoVO;
import com.youlai.system.service.BusCssService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "13.客服信息接口")
@RestController
@RequestMapping("/api/v1/css")
@RequiredArgsConstructor
public class BusCssController {
    private final BusCssService cssService;

    @Operation(summary = "获取当前客服编码", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{cssId}")
    public Result<String> getCssCode(
            @Parameter(description = "客服Id") @PathVariable Long cssId) {
        String cssCode = cssService.getCssCode(cssId);
        return Result.success(cssCode);
    }

    @Operation(summary = "获取当前客服最大订单号", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/max-asn-no/{cssId}")
    public Result<String> getMaxAsnNo(
            @Parameter(description = "客服Id") @PathVariable Long cssId) {
        String maxAsnNo = cssService.getMaxAsnNo(cssId);
        return Result.success(maxAsnNo);
    }
}
