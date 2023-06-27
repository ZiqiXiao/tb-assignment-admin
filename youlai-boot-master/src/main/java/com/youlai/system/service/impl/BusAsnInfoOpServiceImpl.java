package com.youlai.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.system.converter.AsnInfoOpConverter;
import com.youlai.system.mapper.BusAsnInfoMapper;
import com.youlai.system.model.bo.AsnInfoFormBO;
import com.youlai.system.model.entity.BusAsnInfo;
import com.youlai.system.model.entity.BusTechInfo;
import com.youlai.system.model.form.AsnInfoForm;
import com.youlai.system.model.query.AsnInfoOpPageQuery;
import com.youlai.system.model.vo.AsnInfoExportVO;
import com.youlai.system.model.vo.AsnInfoOpPageVO;
import com.youlai.system.model.vo.UserExportVO;
import com.youlai.system.service.BusAsnInfoOpService;
import com.youlai.system.service.BusTechService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusAsnInfoOpServiceImpl extends ServiceImpl<BusAsnInfoMapper, BusAsnInfo> implements BusAsnInfoOpService {

    private final AsnInfoOpConverter asnInfoOpConverter;

    private final BusTechService busTechService;

    @Override
    public Page<AsnInfoOpPageVO> getAsnInfoPage(AsnInfoOpPageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String asnNo = queryParams.getAsnNo();
        String orderNo = queryParams.getOrderNo();
        Integer status = queryParams.getStatus();
        String asnScnCat = queryParams.getAsnScnCat();
        String asnTechCat = queryParams.getAsnTechCat();
        String asnLang = queryParams.getAsnLang();
        String keywords = queryParams.getKeywords();
        Long cssId = queryParams.getCssId();
        Long techId = queryParams.getTechId();

        // 查询数据
        Page<BusAsnInfo> asnInfoPage = this.page(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<BusAsnInfo>()
                        .like(StrUtil.isNotBlank(asnNo), BusAsnInfo::getAsnNo, asnNo)
                        .like(StrUtil.isNotBlank(orderNo), BusAsnInfo::getOrderNo, orderNo)
                        .eq(status != null, BusAsnInfo::getStatus, status)
                        .eq(StrUtil.isNotBlank(asnScnCat), BusAsnInfo::getAsnScnCat, asnScnCat)
                        .eq(StrUtil.isNotBlank(asnTechCat), BusAsnInfo::getAsnTechCat, asnTechCat)
                        .eq(StrUtil.isNotBlank(asnLang), BusAsnInfo::getAsnLang, asnLang)
                        .like(StrUtil.isNotBlank(keywords), BusAsnInfo::getAsnNo, keywords)
                        .eq(cssId != null, BusAsnInfo::getCssId, cssId)
                        .eq(techId != null, BusAsnInfo::getTechId, techId)
                        .select(
                                BusAsnInfo::getId,
                                BusAsnInfo::getAsnNo,
                                BusAsnInfo::getOrderNo,
                                BusAsnInfo::getStatus,
                                BusAsnInfo::getAsnScnCat,
                                BusAsnInfo::getAsnDesc,
                                BusAsnInfo::getAsnTechCat,
                                BusAsnInfo::getAsnLang,
                                BusAsnInfo::getAsnPrice,
                                BusAsnInfo::getTechPortion,
                                BusAsnInfo::getPlatPortion,
                                BusAsnInfo::getCssId,
                                BusAsnInfo::getTechId,
                                BusAsnInfo::getConsultDt,
                                BusAsnInfo::getOrderDt,
                                BusAsnInfo::getCheckDt,
                                BusAsnInfo::getSettlementDt)
        );

        // 实体转换
        Page<AsnInfoOpPageVO> pageResult = asnInfoOpConverter.entity2Page(asnInfoPage);
        return pageResult;
    }

    /**
     * 新增任务
     *
     * @param asnInfoForm 任务表单对象
     * @return
     */
    @Override
    public boolean saveAsn(AsnInfoForm asnInfoForm) {
        calculatePortion(asnInfoForm);

        // 实体转换 form->entity
        BusAsnInfo entity = asnInfoOpConverter.form2Entity(asnInfoForm);

        // 新增用户
        boolean result = this.save(entity);
        return result;
    }


    /**
     * 获取用户详情
     *
     * @param id
     * @return
     */
    @Override
    public AsnInfoForm getAsnInfoFormData(Long id) {
        AsnInfoFormBO asnInfoFormBO = this.baseMapper.getAsnInfoDetail(id);
        System.out.println(asnInfoFormBO);
        // 实体转换po->form
        AsnInfoForm asnInfoForm = asnInfoOpConverter.bo2Form(asnInfoFormBO);
        System.out.println(asnInfoForm);
        return asnInfoForm;
    }

    /**
     * 更新用户
     *
     * @param id   任务ID
     * @param asnInfoForm 任务表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean updateAsnInfo(Long id, AsnInfoForm asnInfoForm) {
        calculatePortion(asnInfoForm);

        // form -> entity
        BusAsnInfo entity = asnInfoOpConverter.form2Entity(asnInfoForm);

        // 修改用户
        boolean result = this.updateById(entity);
        return result;
    }

    @Override
    public List<AsnInfoExportVO> listExportAsn(AsnInfoOpPageQuery queryParams) {
        List<AsnInfoExportVO> list = this.baseMapper.listExportAsnInfo(queryParams);
        return list;
    }

    /*
    * @Param asnInfoForm
    * @return void
    * 计算老师和平台的金额
    * */
    private void calculatePortion(AsnInfoForm asnInfoForm) {
        try {
            BusTechInfo busTechInfo = busTechService.getOne(
                    new LambdaQueryWrapper<BusTechInfo>()
                            .eq(BusTechInfo::getTechId, asnInfoForm.getTechId())
                            .select(BusTechInfo::getRatio));
            if (!busTechInfo.getRatio().isNaN() && asnInfoForm.getAsnPrice() != -1) {
                asnInfoForm.setTechPortion(busTechInfo.getRatio() * asnInfoForm.getAsnPrice());
                asnInfoForm.setPlatPortion(asnInfoForm.getAsnPrice() - asnInfoForm.getTechPortion());
            } else if (asnInfoForm.getAsnPrice() == -1) {
                asnInfoForm.setTechPortion((float) 0);
                asnInfoForm.setPlatPortion((float) 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
