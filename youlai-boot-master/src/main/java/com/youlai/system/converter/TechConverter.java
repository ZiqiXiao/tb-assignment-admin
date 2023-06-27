package com.youlai.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.model.bo.TechBO;
import com.youlai.system.model.vo.TechPageVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechConverter {

    Page<TechPageVO> bo2Vo(Page<TechBO> techBOPage);
}
