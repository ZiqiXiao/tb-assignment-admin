package com.youlai.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.system.model.entity.BusTechInfo;
import com.youlai.system.model.form.TechForm;
import com.youlai.system.model.form.UserForm;
import com.youlai.system.model.query.AsnInfoOpPageQuery;
import com.youlai.system.model.query.TechPageQuery;
import com.youlai.system.model.vo.AsnInfoExportVO;
import com.youlai.system.model.vo.AsnInfoOpPageVO;
import com.youlai.system.model.vo.TechExportVO;
import com.youlai.system.model.vo.TechPageVO;

import java.util.List;

public interface BusTechService  extends IService<BusTechInfo> {

    /**
     * 获取老师信息分页列表
     *
     * @param queryParams 查询参数
     * @return
     */
    Page<TechPageVO> getTechPage(TechPageQuery queryParams);

    /**
     * 获取老师表单数据
     *
     * @param techId 老师ID
     * @return
     */
    TechForm getTechFormData(Long techId);

    /**
     * 新增老师
     *
     * @param techForm 用户表单对象
     * @return
     */
    boolean saveTech(TechForm techForm);

    /**
     * 修改用户
     *
     * @param techId   用户ID
     * @param techForm 用户表单对象
     * @return
     */
    boolean updateTech(Long techId, TechForm techForm);

    /**
     * 获取最大老师Id
     */
    Long getMaxTechId();

    /**
     * 获取导出老师信息列表
     *
     * @param queryParams 查询参数
     * @return
     */
    List<TechExportVO> listExportTech(TechPageQuery queryParams);

    boolean deleteTechs(String techIds);
}
