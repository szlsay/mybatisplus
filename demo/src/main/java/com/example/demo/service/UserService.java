package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

//自定义接口继承通用Iservice接口
public interface UserService extends IService<User> {
}
