package com.youlai.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlai.system.model.bo.AsnInfoFormBO;
import com.youlai.system.model.entity.BusAsnInfo;
import com.youlai.system.model.query.AsnInfoOpPageQuery;
import com.youlai.system.model.vo.AsnInfoExportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusAsnInfoMapper extends BaseMapper<BusAsnInfo> {
    /**
     * 获取任务表单详情
     *
     * @param id
     * @return
     */
    AsnInfoFormBO getAsnInfoDetail(Long id);

    List<AsnInfoExportVO> listExportAsnInfo(AsnInfoOpPageQuery queryParams);
}
