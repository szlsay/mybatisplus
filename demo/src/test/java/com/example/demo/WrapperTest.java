package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    //查询name不为空的用户，并且年龄大于等于18岁
    @Test
    void select0() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name").ge("age", 18);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    //查询名字为Tom的用户
    @Test
    void select1() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "Tom");
        System.out.println(userMapper.selectOne(wrapper));
//        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    //查询年龄18~25岁之间的用户
    @Test
    void select2(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",18,25);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    //模糊查询：查询名字不包含qf的用户,反之like就是包含
    @Test
    void select3(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.notLike("name","qf");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    //模糊查询：包含左侧或者包含右侧具体内容
    @Test
    void select4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //likeLeft和likeRight
        //左右就是 %在左或者在右
        //  e%
        wrapper.likeRight("name","e");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    // 查询用户名中包含Tom并且（年龄大于26或者邮箱为null）的用户 错误写法
    @Test
    void select5(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","Tom").gt("age",26).or().isNull("email");
        userMapper.selectList(wrapper).forEach(System.out::println);

    }

    @Test
    void select6(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","Tom")
                .and( w->w.gt("age",26).or().isNull("email"));
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    //模糊查询：查询名字包含qf的用户，并且按照age升序排序，注意参数不能为空或者null
    @Test
    void select7(){
        //假设用户传递参数
        String name = "qf";
        Integer age = 1;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name),"name",name)
                .orderByAsc(age != null,"age");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    //修改用户id为5的name为Tom
    @Test
    void update0(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",5);
        User user = new User();
        user.setName("Tom");
        userMapper.update(user,wrapper);
    }

    //删除name为Tom的用户
    @Test
    void delete0(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","Tom");
        userMapper.delete(wrapper);
    }

    //修改年龄大于15，并且name为Jack的用户邮箱为 1000@qq.com
    @Test
    void update1(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.gt("age",15)
                .eq("name","Jack")
                .set("email","1000@qq.com");
        userMapper.update(null,wrapper);
    }

    //模糊查询：查询名字包含yy的用户，并且按照age升序排序，注意参数不能为空或者null
    @Test
    void select9(){
        //假设用户传递参数
        String name = "yy";
        Integer age = null;
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name),User::getName,name)
                .orderByAsc(age != null,User::getAge);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    //修改年龄大于26，并且name为yy的用户邮箱为 1000@qq.com
    @Test
    void update2(){
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.gt(User::getAge,26)
                .eq(User::getName,"yy")
                .set(User::getEmail,"1000@qq.com");
        userMapper.update(null,wrapper);
    }

    //通过子查询，查询id等于3的用户信息，此方法也可以进行表关联查询
    @Test
    void select10(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id","select id from user where id=3");
        userMapper.selectObjs(wrapper).forEach(System.out::println);
    }
}

