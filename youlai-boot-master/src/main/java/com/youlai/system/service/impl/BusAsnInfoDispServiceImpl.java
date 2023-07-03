package com.youlai.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.system.converter.AsnInfoDispConverter;
import com.youlai.system.mapper.BusAsnInfoMapper;
import com.youlai.system.model.entity.BusAsnInfo;
import com.youlai.system.model.query.AsnInfoDispPageQuery;
import com.youlai.system.model.vo.AsnInfoDispPageVO;
import com.youlai.system.service.BusAsnInfoDispService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusAsnInfoDispServiceImpl extends ServiceImpl<BusAsnInfoMapper, BusAsnInfo> implements BusAsnInfoDispService {

    private final AsnInfoDispConverter asnInfoDispConverter;

    @Override
    public Page<AsnInfoDispPageVO> getAsnInfoPage(AsnInfoDispPageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();
        String asnTechCat = queryParams.getAsnTechCat();
        String asnLang = queryParams.getAsnLang();
        Float asnPriceUpper = queryParams.getAsnPriceUpper();
        Float asnPriceLower = queryParams.getAsnPriceLower();

        // 查询数据
        Page<BusAsnInfo> asnInfoPage = this.page(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<BusAsnInfo>()
                        .eq(BusAsnInfo::getStatus, 0)
                        .like(StrUtil.isNotBlank(keywords), BusAsnInfo::getAsnDesc, keywords)
                        .eq(StrUtil.isNotBlank(asnTechCat), BusAsnInfo::getAsnTechCat, asnTechCat)
                        .eq(StrUtil.isNotBlank(asnLang), BusAsnInfo::getAsnLang, asnLang)
                        .orderBy(true, false, BusAsnInfo::getAsnPrice)
                        .orderBy(true, false, BusAsnInfo::getConsultDt)
                        .between(
                                asnPriceLower != null && asnPriceUpper != null,
                                BusAsnInfo::getAsnPrice,
                                asnPriceLower,
                                asnPriceUpper)
                        .between(
                                asnPriceLower != null && asnPriceUpper == null,
                                BusAsnInfo::getAsnPrice,
                                asnPriceLower,
                                Long.MAX_VALUE)
                        .between(
                                asnPriceLower == null && asnPriceUpper != null,
                                BusAsnInfo::getAsnPrice,
                                Long.MIN_VALUE,
                                asnPriceUpper)
                        .select(
                                BusAsnInfo::getAsnNo,
                                BusAsnInfo::getAsnDesc,
                                BusAsnInfo::getAsnTechCat,
                                BusAsnInfo::getAsnLang,
                                BusAsnInfo::getAsnPrice,
                                BusAsnInfo::getConsultDt)
        );

        // 实体转换
        Page<AsnInfoDispPageVO> pageResult = asnInfoDispConverter.entity2Page(asnInfoPage);
        return pageResult;
    }
}
