package com.tfchzzy.tfc.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author 田付成
 * @date 2021/5/11 21:10
 */

@Data
public class User {
//    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private  Integer version;
}
