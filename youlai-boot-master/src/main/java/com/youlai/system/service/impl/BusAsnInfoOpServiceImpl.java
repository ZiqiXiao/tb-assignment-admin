package com.youlai.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.system.common.exception.BusinessException;
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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

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
        Page<AsnInfoOpPageVO> asnInfoPage = this.baseMapper.asnInfoPage(new Page<>(pageNum, pageSize), queryParams);
        System.out.println(asnInfoPage);

        return asnInfoPage;
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
        // 计算老师和平台的金额
        calculatePortion(asnInfoForm);

        // 转台转换时设置对应的时间
        setStatusDate(asnInfoForm);

        // form -> entity
        BusAsnInfo entity = asnInfoOpConverter.form2Entity(asnInfoForm);

        // 修改用户
        boolean result = this.updateById(entity);
        return result;
    }

    private void setStatusDate(AsnInfoForm asnInfoForm) {
         BusAsnInfo busAsnInfo= this.getOne(new LambdaQueryWrapper<BusAsnInfo>()
                .eq(BusAsnInfo::getId, asnInfoForm.getId())
                .select(
                        BusAsnInfo::getStatus,
                        BusAsnInfo::getOrderNo,
                        BusAsnInfo::getShipDt,
                        BusAsnInfo::getReceiveDt,
                        BusAsnInfo::getCheckDt,
                        BusAsnInfo::getSettlementDt
                ));
        Integer currentStatus = busAsnInfo.getStatus();
        Integer targetStatus = asnInfoForm.getStatus();
        Date currentDate = Date.valueOf(LocalDate.now());
        System.out.println("Current Date is" + currentDate);
        if (Objects.equals(currentStatus, targetStatus)) {
            if (busAsnInfo.getOrderNo()==null && asnInfoForm.getOrderNo()!=null){
                asnInfoForm.setOrderDt(currentDate);
            }
        }
        if (busAsnInfo.getShipDt()==null && Objects.equals(targetStatus, 3)) {
            asnInfoForm.setOrderDt(currentDate);
            asnInfoForm.setShipDt(currentDate);
        };
        if (busAsnInfo.getReceiveDt()==null && Objects.equals(targetStatus, 4)) {
            if (busAsnInfo.getOrderNo()==null || busAsnInfo.getShipDt()==null){
                throw new BusinessException("请先完成发货操作");
            }
            asnInfoForm.setReceiveDt(currentDate);
            // 计算下个周二的日期并填入核对日期内
            Date checkDate = Date.valueOf(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));
            asnInfoForm.setCheckDt(checkDate);
        }
        if (busAsnInfo.getCheckDt()==null && Objects.equals(targetStatus, 5)) {
                throw new BusinessException("请先完成收货操作");
        }
        if (busAsnInfo.getSettlementDt()==null && Objects.equals(targetStatus, 6)) {
            if (busAsnInfo.getCheckDt()==null || !Objects.equals(currentStatus, 5)){
                throw new BusinessException("请先完成核对操作");
            }
            asnInfoForm.setSettlementDt(currentDate);
        }
    }

    /**
     * @param asnInfoForm 任务表单对象
     * */
    private void calculatePortion(AsnInfoForm asnInfoForm) {
        try {
            BusTechInfo busTechInfo = busTechService.getOne(
                    new LambdaQueryWrapper<BusTechInfo>()
                            .eq(BusTechInfo::getTechId, asnInfoForm.getTechId())
                            .select(BusTechInfo::getRatio));
            if (!busTechInfo.getRatio().isNaN() && asnInfoForm.getAsnPrice() != -1 && asnInfoForm.getTechId() != 60000) {
                asnInfoForm.setTechPortion(busTechInfo.getRatio() * asnInfoForm.getAsnPrice());
                asnInfoForm.setPlatPortion(asnInfoForm.getAsnPrice() - asnInfoForm.getTechPortion());
            } else if (asnInfoForm.getAsnPrice() == -1 || asnInfoForm.getTechId() == 60000) {
                asnInfoForm.setTechPortion((float) 0);
                asnInfoForm.setPlatPortion((float) 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param queryParams 查询参数
     * @return void
     * 计算老师和平台的金额
     * */
    @Override
    public List<AsnInfoExportVO> listExportAsn(AsnInfoOpPageQuery queryParams) {
        List<AsnInfoExportVO> list = this.baseMapper.listExportAsnInfo(queryParams);
        return list;
    }


}
