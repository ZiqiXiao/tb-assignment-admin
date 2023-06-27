package com.youlai.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.common.result.PageResult;
import com.youlai.system.model.query.TechPageQuery;
import com.youlai.system.model.vo.TechPageVO;
import com.youlai.system.service.BusTechService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "14.老师信息接口")
@RestController
@RequestMapping("/api/v1/tech")
@RequiredArgsConstructor
public class BusTechController {
    private final BusTechService busTechService;

    @Operation(summary = "老师信息分页展示", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/disp")
    public PageResult<TechPageVO> getTechPage(
            @ParameterObject
            TechPageQuery queryParams
    ) {
        Page<TechPageVO> result = busTechService.getTechPage(queryParams);
        return PageResult.success(result);
    }
}
