package com.youlai.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.model.bo.TechBO;
import com.youlai.system.model.bo.TechFormBO;
import com.youlai.system.model.entity.BusTechInfo;
import com.youlai.system.model.query.TechPageQuery;
import com.youlai.system.model.vo.TechExportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusTechMapper  extends BaseMapper<BusTechInfo> {
    /**
    * 获取老师信息分页列表
    * @param page 分页对象
    * @param queryParams 查询参数
    * @return Page<TechBO>
    * */
    Page<TechBO> getTechPage(Page<TechBO> page, TechPageQuery queryParams);

    /**
     * 获取老师表单数据
     * @param techId 老师ID
     * @return TechFormBO
     * */
    TechFormBO getTechFormData(Long techId);

    /**
     * 获取老师表单数据
     * @return TechIdMaxBO
     * */
    Long getMaxTechId();

    /**
     * 获取老师导出数据
     * @param queryParams 查询参数
     * @return List<TechExportVO>
     * */
    List<TechExportVO> listExportTech(TechPageQuery queryParams);
}
