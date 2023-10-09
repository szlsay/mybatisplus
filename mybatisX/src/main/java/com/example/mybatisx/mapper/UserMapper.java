package com.example.mybatisx.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.mybatisx.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author szlsay
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-10-09 19:41:06
* @Entity com.example.mybatisx.entity.User
*/
public interface UserMapper extends BaseMapper<User> {
    List<User> selectByName(@Param("name") String name);

    List<User> selectAllOrderById();

    List<User> selectAllOrderByById();

    User searchOneByAge(@Param("age") Integer age);
}




