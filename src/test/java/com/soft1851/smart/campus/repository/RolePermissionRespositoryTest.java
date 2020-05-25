package com.soft1851.smart.campus.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description TODO
 * @Author zhyan
 * @Date 2020/5/23
 * @Version 1.0
 */
@SpringBootTest
class RolePermissionRespositoryTest {
    @Resource
    private RolePermissionRespository rolePermissionRespository;

    @Test
    void getByRole() {
        System.out.println(rolePermissionRespository.getByRole(1));
    }
}