package com.youlai.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.system.common.base.BaseEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@TableName("bus_css_info")
public class BusCssInfo extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long cssId; // 客服编号
    private String cssCode; // 客服代码
    private String cssName; // 客服姓名
    private Date entryDt; // 入职日期
    private Date depDt; // 离职日期
}
