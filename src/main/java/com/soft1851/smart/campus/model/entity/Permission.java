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
public class Permission {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    /**
     * 父类id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 权限名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 权限路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 权限排序
     */
    @Column(name = "sort")
    private Integer sort;
}
