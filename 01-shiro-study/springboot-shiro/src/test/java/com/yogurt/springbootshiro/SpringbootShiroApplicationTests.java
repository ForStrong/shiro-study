package com.yogurt.springbootshiro;

import com.yogurt.springbootshiro.Service.UserService;
import com.yogurt.springbootshiro.dao.SysUserMapper;
import com.yogurt.springbootshiro.model.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShiroApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    SysUserMapper userMapper;
    @Test
    void contextLoads() {
        SysUser u = userService.selectByName("admin");
        System.out.println(u);
    }

}
