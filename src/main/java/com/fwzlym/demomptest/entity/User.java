package com.fwzlym.demomptest.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author fwzlym
 * @Date 2022/2/19 14:30
 * @Description:
 */
@Data
@NoArgsConstructor
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;//create_time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;//update_time

    @TableField(fill = FieldFill.INSERT)//自动填充
    @Version//乐观锁
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic//逻辑删除标志
    private Integer deleted;
}
