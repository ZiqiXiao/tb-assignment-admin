package com.youlai.system.listener.easyexcel;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.youlai.system.converter.TechConverter;
import com.youlai.system.model.entity.BusTechInfo;
import com.youlai.system.model.vo.TechImportVO;
import com.youlai.system.service.BusTechService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TechImportListener extends MyAnalysisEventListener<TechImportVO> {
    // 有效条数
    private int validCount;

    // 无效条数
    private int invalidCount;

    // 导入返回信息
    StringBuilder msg = new StringBuilder();

    private final TechConverter techConverter;

    private final BusTechService busTechService;

    public TechImportListener() {
        this.techConverter = SpringUtil.getBean(TechConverter.class);
        this.busTechService = SpringUtil.getBean(BusTechService.class);
    }

    @Override
    public String getMsg() {
        // 总结信息
        String summaryMsg = StrUtil.format("导入用户结束：成功{}条，失败{}条；<br/>{}", validCount, invalidCount, msg);
        return summaryMsg;
    }

    @Override
    public void invoke(TechImportVO techImportVO, AnalysisContext analysisContext) {
        log.info("解析到一条老师数据:{}", JSONUtil.toJsonStr(techImportVO));

        // 校验数据
        StringBuilder validationMsg = new StringBuilder();

        String techName = techImportVO.getTechName();
        if (StrUtil.isBlank(techName)) {
            validationMsg.append("老师姓名不能为空；");
        }

        String alipay = techImportVO.getAlipay();
        long count = busTechService.count(new LambdaQueryWrapper<BusTechInfo>().eq(BusTechInfo::getAlipay, alipay));
        if (count > 0) {
            validationMsg.append("支付宝账号已存在；");
        }



        if (validationMsg.length() == 0) {
            BusTechInfo entity = techConverter.importVo2Entity(techImportVO);
            boolean saveResult = busTechService.save(entity);
            if (saveResult) {
                validCount++;
            } else {
                invalidCount++;
                msg.append("第" + (validCount + invalidCount) + "行数据保存失败；<br/>");
            }
        }
        else {
            invalidCount++;
            msg.append("第" + (validCount + invalidCount) + "行数据校验失败：").append(validationMsg + "<br/>");
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
