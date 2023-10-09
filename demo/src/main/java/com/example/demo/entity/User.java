package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data //在当前类上提供：get、set、equals、hashcode、toString、@RequiredArgsConstrutor
@AllArgsConstructor //全部参数构造方法
@NoArgsConstructor //无参构造方法
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @Version//乐观锁注解
    private Integer version;

    //插入操作更新字段策略
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //更新操作时更新字段策略
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
