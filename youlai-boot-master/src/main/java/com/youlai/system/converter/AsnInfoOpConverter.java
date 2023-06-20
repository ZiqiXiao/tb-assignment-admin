package com.youlai.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.model.bo.AsnInfoFormBO;
import com.youlai.system.model.bo.UserFormBO;
import com.youlai.system.model.entity.BusAsnInfo;
import com.youlai.system.model.form.AsnInfoForm;
import com.youlai.system.model.form.UserForm;
import com.youlai.system.model.vo.AsnInfoDispPageVO;
import com.youlai.system.model.vo.AsnInfoOpPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AsnInfoOpConverter {

    Page<AsnInfoOpPageVO> entity2Page(Page<BusAsnInfo> page);

    AsnInfoForm bo2Form(AsnInfoFormBO bo);

    AsnInfoForm entity2Form(BusAsnInfo entity);

    @InheritInverseConfiguration(name="entity2Form")
    BusAsnInfo form2Entity(AsnInfoForm entity);
}
