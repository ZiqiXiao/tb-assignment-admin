package com.youlai.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.system.mapper.BusTechMapper;
import com.youlai.system.model.entity.BusTechInfo;
import com.youlai.system.service.BusTechService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusTechServiceImpl extends ServiceImpl<BusTechMapper, BusTechInfo> implements BusTechService {

}
