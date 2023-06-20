package com.youlai.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.common.annotation.PreventDuplicateSubmit;
import com.youlai.system.common.result.PageResult;
import com.youlai.system.common.result.Result;
import com.youlai.system.model.form.AsnInfoForm;
import com.youlai.system.model.query.AsnInfoDispPageQuery;
import com.youlai.system.model.vo.AsnInfoDispPageVO;
import com.youlai.system.service.BusAsnInfoDispService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@Tag(name = "11.任务信息展示接口")
@RestController
@RequestMapping("/api/v1/asn-disp")
@RequiredArgsConstructor
public class BusAsnInfoDispController {

    private final BusAsnInfoDispService busAsnInfoDispService;

    @Operation(summary = "任务信息展示分页列表", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/disp")
    public PageResult<AsnInfoDispPageVO> getAsnInfoDispPage(
            @ParameterObject AsnInfoDispPageQuery queryParams
    ) {
        Page<AsnInfoDispPageVO> result = busAsnInfoDispService.getAsnInfoPage(queryParams);
        return PageResult.success(result);
    }


}
