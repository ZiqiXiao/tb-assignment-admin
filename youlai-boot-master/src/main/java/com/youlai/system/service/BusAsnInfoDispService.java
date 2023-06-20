package com.youlai.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.system.model.entity.BusAsnInfo;
import com.youlai.system.model.form.AsnInfoForm;
import com.youlai.system.model.query.AsnInfoDispPageQuery;
import com.youlai.system.model.vo.AsnInfoDispPageVO;

public interface BusAsnInfoDispService extends IService<BusAsnInfo> {
    /**
     * 获取任务信息分页列表
     *
     * @param queryParams
     * @return
     */
    Page<AsnInfoDispPageVO> getAsnInfoPage(AsnInfoDispPageQuery queryParams);
}
