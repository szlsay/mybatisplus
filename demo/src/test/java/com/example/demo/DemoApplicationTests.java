package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.*;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
//    int deleteById(Serializable id);
    @Test
    void deleteById() {
        int number = userMapper.deleteById(10L);
        System.out.println(number);
    }

    /**
     * 根据实体(ID)删除
     *
     * @param entity 实体对象
     * @since 3.4.4
     */
//    int deleteById(T entity);
    @Test
    void deleteByEntity() {
        User user = new User();
        user.setId(7L);
        int number = userMapper.deleteById(user);
        System.out.println(number);
    }
    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     */
//    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
    @Test
    void deleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "J");
        map.put("age", 18);
        int number = userMapper.deleteByMap(map);
        System.out.println(number);
    }
    /**
     * 根据 entity 条件，删除记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
//    int delete(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    @Test
    void delete() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("name", "J");
        int number = userMapper.delete(wrapper);
        System.out.println(number);
    }

    /**
     * 删除（根据ID或实体 批量删除）
     *
     * @param idList 主键ID列表或实体列表(不能为 null 以及 empty)
     */
//    int deleteBatchIds(@Param(Constants.COLL) Collection<?> idList);
    @Test
    void deleteBatchIds() {
        List<Long> idList = new ArrayList<>();
        idList.add(8L);
        idList.add(9L);
        int number = userMapper.deleteBatchIds(idList);
        System.out.println(number);
    }
}
