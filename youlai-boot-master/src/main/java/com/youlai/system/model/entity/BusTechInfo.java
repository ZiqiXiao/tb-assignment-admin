package com.youlai.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.system.common.base.BaseEntity;
import lombok.Data;

import java.time.LocalDate;
import java.sql.Date;

@Data
@TableName("bus_tech_info")
public class BusTechInfo extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long techId; //
    private String techName; //
    private String alipay; //
    private Float ratio; //
    private Date entryDt; //
    private Date depDt; //

}
