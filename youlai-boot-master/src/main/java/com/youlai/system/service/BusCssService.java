package com.youlai.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.system.model.entity.BusCssInfo;

public interface BusCssService extends IService<BusCssInfo> {
    /**
     * 获取客服编码
     *
     * @param cssId 客服ID
     * @return cssCode 客服编码
     */
    String getCssCode(Long cssId);

    /**
     * 获取客服最大订单号
     *
     * @param cssId 客服ID
     * @return asnNo 客服最大订单号
     */
    String getMaxAsnNo(Long cssId);
}
