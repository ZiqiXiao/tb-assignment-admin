package com.youlai.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.model.bo.TechBO;
import com.youlai.system.model.bo.TechFormBO;
import com.youlai.system.model.entity.BusTechInfo;
import com.youlai.system.model.form.TechForm;
import com.youlai.system.model.vo.TechImportVO;
import com.youlai.system.model.vo.TechPageVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechConverter {

    Page<TechPageVO> bo2Vo(Page<TechBO> techBOPage);

    TechForm bo2Form(TechFormBO techFormBO);

    BusTechInfo form2Entity(TechForm entity);

    BusTechInfo importVo2Entity(TechImportVO techImportVO);
}
