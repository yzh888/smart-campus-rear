package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description TODO
 * @Author zhyan
 * @Date 2020/5/19
 * @Version 1.0
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
