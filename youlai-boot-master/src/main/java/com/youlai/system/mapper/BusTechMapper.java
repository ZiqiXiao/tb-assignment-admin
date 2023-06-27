package com.youlai.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.model.bo.TechBO;
import com.youlai.system.model.bo.UserBO;
import com.youlai.system.model.entity.BusTechInfo;
import com.youlai.system.model.query.TechPageQuery;
import com.youlai.system.model.query.UserPageQuery;
import com.youlai.system.model.vo.TechPageVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusTechMapper  extends BaseMapper<BusTechInfo> {
    Page<TechBO> getTechPage(Page<TechBO> page, TechPageQuery queryParams);
}
