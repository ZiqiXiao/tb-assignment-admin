package com.youlai.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.system.mapper.BusCssInfoMapper;
import com.youlai.system.model.bo.CssMaxAsnNoBO;
import com.youlai.system.model.entity.BusCssInfo;
import com.youlai.system.service.BusCssService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusCssServiceImpl extends ServiceImpl<BusCssInfoMapper, BusCssInfo> implements BusCssService {

    @Override
    public String getCssCode(Long cssId) {
        String cssCode = this.getById(cssId).getCssCode();
        return cssCode;
    }

    @Override
    public String getMaxAsnNo(Long cssId) {
        CssMaxAsnNoBO maxAsnNo = this.baseMapper.getMaxAsnNo(cssId);
        if (maxAsnNo == null || maxAsnNo.getAsnNo() == null) {
            return "tb-x-100100";
        }
        else {
            return maxAsnNo.getAsnNo();
        }
    }

}
