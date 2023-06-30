package com.youlai.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.system.converter.TechConverter;
import com.youlai.system.mapper.BusTechMapper;
import com.youlai.system.model.bo.TechBO;
import com.youlai.system.model.bo.TechFormBO;
import com.youlai.system.model.entity.BusTechInfo;
import com.youlai.system.model.form.TechForm;
import com.youlai.system.model.query.TechPageQuery;
import com.youlai.system.model.vo.TechExportVO;
import com.youlai.system.model.vo.TechPageVO;
import com.youlai.system.service.BusTechService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusTechServiceImpl extends ServiceImpl<BusTechMapper, BusTechInfo> implements BusTechService {

    private final TechConverter techConverter;


    /**
     * 分页查询老师列表
     *
     * @param queryParams 查询参数
     * @return
     */
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

    /**
     * 根据id获取老师信息
     *
     * @param techId 老师ID
     * @return
     */
    @Override
    public TechForm getTechFormData(Long techId) {
        TechFormBO techFormBO = this.baseMapper.getTechFormData(techId);

        TechForm techForm = techConverter.bo2Form(techFormBO);
        System.out.println(techForm);
        return techForm;
    }

    /**
     * 新增老师
     *
     * @param techForm 用户表单对象
     * @return
     */
    @Override
    public boolean saveTech(TechForm techForm) {

        String alipay = techForm.getAlipay();

        long count = this.count(new LambdaQueryWrapper<BusTechInfo>().eq(BusTechInfo::getAlipay, alipay));
        Assert.isTrue(count == 0, "支付宝账号已存在");

        // 实体转换 form->entity
        BusTechInfo entity = techConverter.form2Entity(techForm);

        // 新增用户
        boolean result = this.save(entity);

        return result;
    }

    /**
     * 更新用户
     *
     * @param techId   老师ID
     * @param techForm 老师表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean updateTech(Long techId, TechForm techForm) {

        String alipay = techForm.getAlipay();

        long count = this.count(new LambdaQueryWrapper<BusTechInfo>()
                .eq(BusTechInfo::getAlipay, alipay)
        );
        Assert.isTrue(count == 0, "支付宝账号已存在");

        // form -> entity
        BusTechInfo entity = techConverter.form2Entity(techForm);

        // 修改老师
        boolean result = this.updateById(entity);

        return result;
    }

    /**
     * 获取最大的老师ID
     *
     * @return
     */
    @Override
    public Long getMaxTechId() {
        Long techIdMax = this.baseMapper.getMaxTechId();
        return Objects.requireNonNullElse(techIdMax, 60000L);
    }

    /**
     * 导出老师列表
     *
     * @param queryParams 查询参数
     * @return
     */
    @Override
    public List<TechExportVO> listExportTech(TechPageQuery queryParams) {
        List<TechExportVO> techExportVOList = this.baseMapper.listExportTech(queryParams);
        System.out.println(techExportVOList);
        return techExportVOList;
    }

    /**
     * 删除用户
     *
     * @param techIdsStr 老师ID，多个以英文逗号(,)分割
     * @return
     */
    @Override
    public boolean deleteTechs(String techIdsStr) {
        Assert.isTrue(StrUtil.isNotBlank(techIdsStr), "删除的老师数据为空");
        // 逻辑删除
        System.out.println(techIdsStr);
        List<Long> ids = Arrays.asList(techIdsStr.split(",")).stream()
                .map(idStr -> Long.parseLong(idStr)).collect(Collectors.toList());
        boolean result = this.removeByIds(ids);
        return result;
    }
}
