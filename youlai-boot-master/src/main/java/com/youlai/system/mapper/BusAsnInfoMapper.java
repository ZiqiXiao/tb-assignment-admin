package com.youlai.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.model.bo.AsnInfoFormBO;
import com.youlai.system.model.entity.BusAsnInfo;
import com.youlai.system.model.query.AsnInfoOpPageQuery;
import com.youlai.system.model.vo.AsnInfoExportVO;
import com.youlai.system.model.vo.AsnInfoOpPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusAsnInfoMapper extends BaseMapper<BusAsnInfo> {
    /**
     * 获取任务表单详情
     *
     * @param id 任务ID
     * @return
     */
    AsnInfoFormBO getAsnInfoDetail(Long id);

    List<AsnInfoExportVO> listExportAsnInfo(AsnInfoOpPageQuery queryParams);

    Page<AsnInfoOpPageVO> asnInfoPage(Page page , AsnInfoOpPageQuery queryParams);
}
