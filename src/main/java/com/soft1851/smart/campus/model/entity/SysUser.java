package com.soft1851.smart.campus.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description TODO
 * @Author zhyan
 * @Date 2020/5/19
 * @Version 1.0
 */
@Data
@Entity
public class SysUser {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sysUserId;

    /**
     * 管理员账号
     */
    @Column(name = "sys_user_name")
    private String sysUserName;

    /**
     * 账号密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 盐（私钥）
     */
    @Column(name = "salt")
    private String salt;

    @Column(name = "role_id")
    private Integer roleId;
}
