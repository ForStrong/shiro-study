package com.itaem.crazy.shirodemo.modules.shiro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 权限实体类
 */
@Getter
@Setter
@Entity
public class Permission {

    @Id
    private Integer permissionId;
    private String permissionName;
    private String permission;
}
