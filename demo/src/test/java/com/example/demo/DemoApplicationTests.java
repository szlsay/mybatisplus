package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
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
    /**
     * 插入一条记录
     *
     * entity 实体对象
     */
//    int insert(T entity);
    @Test
    void insert() {
        User user = new User();
        user.setName("yy");
        System.out.println(userMapper.insert(user));
    }
    /**
     * 根据 ID 查询
     * id 主键ID
     */
//    T selectById(Serializable id);
    @Test
    void selectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    /**
     * 查询（根据ID 批量查询）
     * idList 主键ID列表(不能为 null 以及 empty)
     */
//    List<T> selectBatchIds(@Param(Constants.COLL) Collection<? extends Serializable> idList);
    @Test
    void selectBatchIds() {
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        List<User> users = userMapper.selectBatchIds(idList);
        System.out.println(users);
    }
    /**
     * 查询（根据 columnMap 条件）
     * columnMap 表字段 map 对象
     */
//    List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
    @Test
    void selectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    /**
     * 根据 ID 修改
     * entity 实体对象
     */
//    int updateById(@Param(Constants.ENTITY) T entity);
    @Test
    void updateById() {
        User user = new User();
        user.setId(10L);
        user.setName("A");
        int number = userMapper.updateById(user);
        System.out.println(number);
    }

    /**
     * 根据 whereEntity 条件，更新记录
     * entity        实体对象 (set 条件值,可以为 null)
     * updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
//    int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);
    @Test
    void update() {
        User user = new User();
        user.setId(10L);
        user.setName("A");
        int number = userMapper.updateById(user);
        System.out.println(number);
    }

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 根据 ID 删除
     * id 主键ID
     */
//    int deleteById(Serializable id);
    @Test
    void deleteById() {
        int number = userMapper.deleteById(10L);
        System.out.println(number);
    }

    /**
     * 根据实体(ID)删除
     * entity 实体对象
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
     * columnMap 表字段 map 对象
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
     * queryWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
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
     * idList 主键ID列表或实体列表(不能为 null 以及 empty)
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
