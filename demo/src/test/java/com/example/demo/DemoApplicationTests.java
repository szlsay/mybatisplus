package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
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

    @Autowired
    private UserService userService;

    @Test
    void paginationInner(){
        //简单分页模型
        //current – 当前页
        //size – 每页显示条数
        //底层逻辑就是使用Limit分页的公式 (index-1)*pageSize
        Page<User> page = new Page<>(2,3);
        userMapper.selectPage(page,null);

        //获取记录
        List<User> users = page.getRecords();
        users.forEach(user -> System.out.println(user));
        System.out.println(page.getPages());//获取总页数
        System.out.println(page.getTotal());//获取总数据量
        System.out.println(page.hasNext());//是否有下一页
        System.out.println(page.hasPrevious());//是否有上一页
    }

    //单线程成功测试
    @Test
    void mybatisOptimisticLocker(){
        //查询用户信息（查出version）
        User user = userMapper.selectById(3L);
        //修改用户信息
        user.setName("abc");
        user.setAge(10);
        //执行更新
        userMapper.updateById(user);
    }

    //模仿多线程争抢资源
    @Test
    void mybatisOptimisticLocker2(){
        //----------------------线程1-----------------------
        //查询用户信息（查出version）
        User user1 = userMapper.selectById(3L);
        //修改用户信息
        user1.setName("abc");
        user1.setAge(10);
        //----------------------线程2-----------------------
        //查询用户信息（查出version）
        User user2 = userMapper.selectById(3L);
        //修改用户信息
        user2.setName("efg");
        user2.setAge(18);

        //先执行线程2更新
        userMapper.updateById(user2);

        //执行线程1更新
        userMapper.updateById(user1);
    }

    @Test
    void insertMore() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("a" + i);
            users.add(user);
        }
        System.out.println(userService.saveBatch(users));
    }
    @Test
    void selectByAge() {
        System.out.println(userMapper.selectByAge(28));
    }

    @Test
    void selectByName() {
        System.out.println(userMapper.selectByName("A"));
    }
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
        user.setName("AB");
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
