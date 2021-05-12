package com.tfchzzy.tfc;

import com.tfchzzy.tfc.entity.User;
import com.tfchzzy.tfc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void insert(){
        User user = new User();
        user.setAge(20);
        user.setEmail("3100228060@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

}
