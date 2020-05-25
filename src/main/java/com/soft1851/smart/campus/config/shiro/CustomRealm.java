package com.soft1851.smart.campus.config.shiro;

import com.soft1851.smart.campus.model.entity.Permission;
import com.soft1851.smart.campus.model.entity.SysUser;
import com.soft1851.smart.campus.repository.RolePermissionRespository;
import com.soft1851.smart.campus.repository.SysUserRepository;
import com.soft1851.smart.campus.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description TODO
 * @Author zhyan
 * @Date 2020/5/19
 * @Version 1.0
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private RolePermissionRespository rolePermissionRespository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userId = JWTUtil.getUserId(principalCollection.toString());
        //获取用户信息
        //SysUser user = sysUserRepository.getBySysUserId(Long.parseLong(userId));
        List<Permission> list = rolePermissionRespository.getByRole(Long.parseLong(userId));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取用户权限
        Set<String> userPermission = new HashSet<>();
        list.forEach(permission -> {
            userPermission.add(permission.getName());
        });
        assert false;
        log.info(userPermission.toString());
        info.setStringPermissions(userPermission);
        return info;
    }

    /**
     * 使用JWT替代原生Token
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String token = (String) authcToken.getPrincipal();
        String userId = JWTUtil.getUserId(token);
        if (userId == null) {
            throw new AuthenticationException("token invalid");
        }
        //根据用户名从数据库获取密码
        System.out.println(userId);
        SysUser user = sysUserRepository.getBySysUserId(Long.parseLong(userId));
        if (user == null) {
            throw new AccountException("用户名不正确");
        }
        if (!JWTUtil.deToken(token, userId, user.getPassword())) {
            throw new AuthenticationException("用户名或密码不正确");
        }
        log.info(">>>>>>>>>>>>>>>>>>>登录认证>>>>>>>>>>>>>>");
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
