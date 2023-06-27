package com.youlai.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.system.model.entity.BusTechInfo;
import com.youlai.system.model.query.AsnInfoOpPageQuery;
import com.youlai.system.model.query.TechPageQuery;
import com.youlai.system.model.vo.AsnInfoOpPageVO;
import com.youlai.system.model.vo.TechPageVO;

public interface BusTechService  extends IService<BusTechInfo> {

    /**
     * 获取任务信息分页列表
     *
     * @param queryParams
     * @return
     */
    Page<TechPageVO> getTechPage(TechPageQuery queryParams);
}
