package com.yogurt.springbootshiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置类
 *  shiro的几个重要对象                                             springboot中的实现
 *      subject 当前用户                                         ShiroFilterFactoryBean
 *      securityManager 管理所有用户                             DefaultWebSecurityManager
 *      realm 控制数据访问   实现认证和授权                             自定义实现
 */
@Configuration
public class ShiroConfig {



    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();
        /*
            anon : 无需认证，就可以访问
            authc : 必须认证，才能访问
            user : 必须拥有 “记住我”功能才能用
            perms : 拥有对某个资源的权限才能访问
            role : 拥有某个角色权限才能访问
         */
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/user/add","perms[user:add]");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        shiroFilter.setLoginUrl("/toLogin"); //未认证的用户直接跳到登陆页面
        return shiroFilter;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
