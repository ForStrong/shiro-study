package com.yogurt.springbootshiro.Service;

import com.yogurt.springbootshiro.model.SysUser;

public interface UserService {
    SysUser selectByName(String name);
}
