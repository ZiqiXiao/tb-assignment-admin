package com.youlai.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlai.system.model.bo.CssMaxAsnNoBO;
import com.youlai.system.model.entity.BusCssInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusCssInfoMapper   extends BaseMapper<BusCssInfo> {
    /**
     * 获取客服最大订单号
     *
     * @param cssId
     * @return
     */
    CssMaxAsnNoBO getMaxAsnNo(Long cssId);
}
