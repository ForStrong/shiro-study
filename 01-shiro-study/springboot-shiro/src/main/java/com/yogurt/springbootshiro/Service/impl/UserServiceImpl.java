package com.yogurt.springbootshiro.Service.impl;

import com.yogurt.springbootshiro.Service.UserService;
import com.yogurt.springbootshiro.dao.SysUserMapper;
import com.yogurt.springbootshiro.model.SysUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private SysUserMapper userMapper;

    public UserServiceImpl (SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }
}
