package com.youlai.system.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.common.annotation.PreventDuplicateSubmit;
import com.youlai.system.common.constant.ExcelConstants;
import com.youlai.system.common.result.PageResult;
import com.youlai.system.common.result.Result;
import com.youlai.system.common.util.ExcelUtils;
import com.youlai.system.listener.easyexcel.TechImportListener;
import com.youlai.system.model.form.TechForm;
import com.youlai.system.model.query.TechPageQuery;
import com.youlai.system.model.vo.*;
import com.youlai.system.service.BusTechService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Tag(name = "14.老师信息接口")
@RestController
@RequestMapping("/api/v1/tech")
@RequiredArgsConstructor
public class BusTechController {
    private final BusTechService busTechService;

    @Operation(summary = "老师信息分页展示", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/page")
    public PageResult<TechPageVO> getTechPage(
            @ParameterObject
            TechPageQuery queryParams
    ) {
        Page<TechPageVO> result = busTechService.getTechPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "老师表单数据", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{techId}/form")
    public Result<TechForm> getTechForm(
            @Parameter(description = "用户ID") @PathVariable Long techId
    ) {
        TechForm formData = busTechService.getTechFormData(techId);
        return Result.success(formData);
    }

    @Operation(summary = "新增老师", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping
//    @PreAuthorize("@ss.hasPerm('sys:user:add')")
    @PreventDuplicateSubmit
    public Result saveUser(
            @RequestBody @Valid TechForm techForm
    ) {
        boolean result = busTechService.saveTech(techForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除老师", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{techIds}")
//    @PreAuthorize("@ss.hasPerm('sys:user:delete')")
    public Result deleteUsers(
            @Parameter(description = "老师ID，多个以英文逗号(,)分割") @PathVariable String techIds
    ) {
        boolean result = busTechService.deleteTechs(techIds);
        return Result.judge(result);
    }

    @Operation(summary = "修改老师", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping(value = "/{techId}")
//    @PreAuthorize("@ss.hasPerm('sys:user:edit')")
    public Result updateTech(
            @Parameter(description = "老师ID") @PathVariable Long techId,
            @RequestBody @Validated TechForm techForm) {
        boolean result = busTechService.updateTech(techId, techForm);
        return Result.judge(result);
    }


    @Operation(summary = "获取当前最大老师Id", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/max-tech-id")
    public Result<Long> getMaxTechId() {
        Long maxAsnNo = busTechService.getMaxTechId();
        return Result.success(maxAsnNo);
    }

    @Operation(summary = "导出老师", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/_export")
    public void exportTech(TechPageQuery queryParams, HttpServletResponse response) throws IOException {
        String fileName = "老师列表.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

        List<TechExportVO> exportUserList = busTechService.listExportTech(queryParams);
        EasyExcel.write(response.getOutputStream(), TechExportVO.class).sheet("用户列表")
                .doWrite(exportUserList);
    }

    @Operation(summary = "导入老师", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/_import")
    public Result importTech(MultipartFile file) throws IOException {
        TechImportListener listener = new TechImportListener();
        String msg = ExcelUtils.importExcel(file.getInputStream(), TechImportVO.class, listener);
        return Result.success(msg);
    }

    @Operation(summary = "老师导入模板下载", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/template")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        String fileName = "老师导入模板.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

        String fileClassPath = ExcelConstants.EXCEL_TEMPLATE_DIR + File.separator + fileName;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);

        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();

        excelWriter.finish();
    }

}
