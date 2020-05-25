package com.soft1851.smart.campus.model.entity;

import com.sun.istack.NotNull;
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
public class Role {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 角色描述
     */
    @Column(name = "description")
    private String description;
}
