package com.yogurt.springbootshiro.config;

import com.yogurt.springbootshiro.Service.UserService;
import com.yogurt.springbootshiro.model.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权 -> doGetAuthorizationInfo");
        //principalCollection.getPrimaryPrincipal() 这个是SimpleAuthenticationInfo的第一个参数
        String username = (String) principalCollection.getPrimaryPrincipal();
        SysUser user = userService.selectByName(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermission(user.getPerms());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证 -> doGetAuthorizationInfo");
        //String username = "root";
        //String password = "root";
        //UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //if (!username.equals(token.getUsername())) //这回抛出用户名不存在异常
        //    return null;
        //return new SimpleAuthenticationInfo("",password,"");

        //结合数据库操作
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        SysUser user = userService.selectByName(token.getUsername());
        if (user==null) {
            return null;//抛出用户名不存在异常
        }
        //密码交给shiro处理
        return new SimpleAuthenticationInfo(user.getUserName(),user.getUserPassword(),"");
    }
}
