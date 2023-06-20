package com.youlai.system.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.common.annotation.PreventDuplicateSubmit;
import com.youlai.system.common.result.PageResult;
import com.youlai.system.common.result.Result;
import com.youlai.system.model.form.AsnInfoForm;
import com.youlai.system.model.form.UserForm;
import com.youlai.system.model.query.AsnInfoDispPageQuery;
import com.youlai.system.model.query.AsnInfoOpPageQuery;
import com.youlai.system.model.query.UserPageQuery;
import com.youlai.system.model.vo.AsnInfoDispPageVO;
import com.youlai.system.model.vo.AsnInfoExportVO;
import com.youlai.system.model.vo.AsnInfoOpPageVO;
import com.youlai.system.model.vo.UserExportVO;
import com.youlai.system.service.BusAsnInfoDispService;
import com.youlai.system.service.BusAsnInfoOpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Tag(name = "12.任务信息操作接口")
@RestController
@RequestMapping("/api/v1/asn-op")
@RequiredArgsConstructor
public class BusAsnInfoOpController {
    private final BusAsnInfoOpService busAsnInfoOpService;

    @Operation(summary = "任务信息展示分页列表", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/disp")
    public PageResult<AsnInfoOpPageVO> getAsnInfoOpPage(
            @ParameterObject AsnInfoOpPageQuery queryParams
    ) {
        Page<AsnInfoOpPageVO> result = busAsnInfoOpService.getAsnInfoPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增记录", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping ("/add")
//    @PreAuthorize("@ss.hasPerm('sys:user:add')")
    @PreventDuplicateSubmit
    public Result saveUser(
            @RequestBody @Valid AsnInfoForm asnInfoForm
    ) {
        boolean result = busAsnInfoOpService.saveAsn(asnInfoForm);
        return Result.judge(result);
    }

    @Operation(summary = "用户表单数据", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{id}/form")
    public Result<AsnInfoForm> getUserForm(
            @Parameter(description = "用户ID") @PathVariable Long id
    ) {
        AsnInfoForm formData = busAsnInfoOpService.getAsnInfoFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改任务", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping(value = "update/{id}")
//    @PreAuthorize("@ss.hasPerm('sys:user:edit')")
    public Result updateUser(
            @Parameter(description = "任务ID") @PathVariable Long id,
            @RequestBody @Validated AsnInfoForm asnInfoForm) {
        boolean result = busAsnInfoOpService.updateAsnInfo(id, asnInfoForm);
        return Result.judge(result);
    }

    @Operation(summary = "导出任务", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/_export")
    public void exportUsers(AsnInfoOpPageQuery queryParams, HttpServletResponse response) throws IOException {
        String fileName = "任务列表.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

        List<AsnInfoExportVO> exportAsnInfoList = busAsnInfoOpService.listExportAsn(queryParams);
        EasyExcel.write(response.getOutputStream(), AsnInfoExportVO.class).sheet("任务列表")
                .doWrite(exportAsnInfoList);
    }
}
