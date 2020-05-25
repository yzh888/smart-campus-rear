package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.Permission;
import com.soft1851.smart.campus.model.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description TODO
 * @Author zhyan
 * @Date 2020/5/23
 * @Version 1.0
 */
public interface RolePermissionRespository extends JpaRepository<RolePermission, Integer> {

    @Query("SELECT u FROM RolePermission a, Permission u, SysUser b WHERE a.roleId=b.roleId " +
            "AND a.permissionId=u.permissionId AND b.sysUserId=?1")
    List<Permission> getByRole(long id);
}
