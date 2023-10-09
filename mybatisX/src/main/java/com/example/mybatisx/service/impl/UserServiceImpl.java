package com.example.mybatisx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisx.entity.User;
import com.example.mybatisx.service.UserService;
import com.example.mybatisx.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author szlsay
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-10-09 19:41:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




