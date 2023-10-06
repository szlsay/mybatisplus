package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    //一旦继承BaseMapper以后，几乎实现了常见所有的CRUD操作

    //自定义CRUD方法
    /**
     * BaseMapper中包含常用的，或者通用的CRUD方法，但是如果我们自己的业务有一些特殊的CRUD操作，
     * 我们可以在当前这个接口中进行自定义，那么这个操作就和之前的MyBatis操作没有区别了。
     */
}
