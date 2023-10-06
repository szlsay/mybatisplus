package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //在当前类上提供：get、set、equals、hashcode、toString、@RequiredArgsConstrutor
@AllArgsConstructor //全部参数构造方法
@NoArgsConstructor //无参构造方法
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
