package com.tfchzzy.tfc;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tfchzzy.tfc.entity.User;
import com.tfchzzy.tfc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class TfcApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
//    private User user;

    //查询User表中的所有数据
    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);

    }

    @Test
    void insert() {
        User user = new User();
        user.setAge(77);
        user.setName("67");
        user.setEmail("1111@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1392507867952578562L);
        user.setAge(3);
        int i = userMapper.updateById(user);
        System.out.println(i);

    }

    //测试乐观锁,先查询在修改
    @Test
    void testlock() {
        //根据id查询数据
        User user = userMapper.selectById(1405473018142101506L);
        user.setAge(66);
        userMapper.updateById(user);

    }

    //循环查询
    @Test
    void testforeach() {
        //根据id查询数据
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);

    }

    @Test
    void testPage() {
        //1.创建page对象
        //2.传入两个参数 ：当前页 和 每页显示记录数
        Page<User> page = new Page<>(1, 3);
        //调用mp分页查询的方法。
        //分页的所有数据放入page
        userMapper.selectPage(page,null);
        //当前页
        System.out.println(page.getCurrent());
        //每页list合集
        System.out.println(page.getRecords());
        //每页显示记录数
        System.out.println(page.getPages());
        //总记录数
        System.out.println(page.getTotal());
        //显示每页记录数
        System.out.println(page.getSize());
        //下一页
        System.out.println(page.hasNext());
        //上一页
        System.out.println(page.hasPrevious());

    }


    //物理删除
    @Test
    void testDelete() {
        int result = userMapper.deleteById(1406841533369425922l);
        System.out.println(result);
    }
}
