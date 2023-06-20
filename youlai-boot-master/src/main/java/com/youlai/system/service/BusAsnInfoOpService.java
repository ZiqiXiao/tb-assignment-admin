package com.youlai.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.system.model.entity.BusAsnInfo;
import com.youlai.system.model.form.AsnInfoForm;
import com.youlai.system.model.form.UserForm;
import com.youlai.system.model.query.AsnInfoOpPageQuery;
import com.youlai.system.model.query.UserPageQuery;
import com.youlai.system.model.vo.AsnInfoExportVO;
import com.youlai.system.model.vo.AsnInfoOpPageVO;
import com.youlai.system.model.vo.UserExportVO;

import java.util.List;

public interface BusAsnInfoOpService extends IService<BusAsnInfo> {
    /**
     * 获取任务信息分页列表
     *
     * @param queryParams
     * @return
     */
    Page<AsnInfoOpPageVO> getAsnInfoPage(AsnInfoOpPageQuery queryParams);

    /**
     * 新增任务
     *
     * @param asnInfoForm 任务表单对象
     * @return
     */
    boolean saveAsn(AsnInfoForm asnInfoForm);

    /**
     * 获取任务表单详情
     *
     * @param id 用户ID
     * @return
     */
    AsnInfoForm getAsnInfoFormData(Long id);

    /**
     * 修改任务
     *
     * @param id   任务ID
     * @param asnInfoForm 任务表单对象
     * @return
     */
    boolean updateAsnInfo(Long id, AsnInfoForm asnInfoForm);

    /**
     * 获取导出任务信息列表
     *
     * @param queryParams
     * @return
     */
    List<AsnInfoExportVO> listExportAsn(AsnInfoOpPageQuery queryParams);
}
