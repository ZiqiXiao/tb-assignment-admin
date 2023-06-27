package com.youlai.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.system.converter.TechConverter;
import com.youlai.system.mapper.BusTechMapper;
import com.youlai.system.model.bo.TechBO;
import com.youlai.system.model.entity.BusTechInfo;
import com.youlai.system.model.query.TechPageQuery;
import com.youlai.system.model.vo.TechPageVO;
import com.youlai.system.service.BusTechService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusTechServiceImpl extends ServiceImpl<BusTechMapper, BusTechInfo> implements BusTechService {

    private final TechConverter techConverter;

    @Override
    public Page<TechPageVO> getTechPage(TechPageQuery queryParams) {

        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();

        Page<TechBO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<TechBO> techBOPage = this.baseMapper.getTechPage(page, queryParams);

        // 实体转换
        Page<TechPageVO> techPageVO = techConverter.bo2Vo(techBOPage);

        return techPageVO;

    }
}
