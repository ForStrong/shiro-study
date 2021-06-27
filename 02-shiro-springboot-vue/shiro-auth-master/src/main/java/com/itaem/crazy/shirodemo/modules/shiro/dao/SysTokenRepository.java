package com.itaem.crazy.shirodemo.modules.shiro.dao;

import com.itaem.crazy.shirodemo.modules.shiro.entity.SysToken;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SysTokenRepository extends JpaRepository<SysToken, Integer> {
    /**
     * 通过token查找
     * @param token
     * @return
     */
    SysToken findByToken(String token);

    /**
     * 通过userID查找
     * @param userId
     * @return
     */
    SysToken findByUserId(Integer userId);
}
