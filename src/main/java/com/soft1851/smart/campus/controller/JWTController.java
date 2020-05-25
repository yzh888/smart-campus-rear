package com.soft1851.smart.campus.controller;

import com.soft1851.smart.campus.constant.ResponseResult;
import com.soft1851.smart.campus.model.entity.SysUser;
import com.soft1851.smart.campus.repository.SysUserRepository;
import com.soft1851.smart.campus.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author zhyan
 * @Date 2020/5/23
 * @Version 1.0
 */
@RestController
@Slf4j
public class JWTController {

    @Resource
    private SysUserRepository sysUserRepository;

    @PostMapping("/login")
    public ResponseResult login(@RequestParam("userId") String userId,
                        @RequestParam("password") String password) {
        SysUser user = sysUserRepository.getBySysUserId(Long.parseLong(userId));
        if (user.getPassword().equals(password)) {
            return ResponseResult.success(JWTUtil.getToken(userId, password));
        }else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/article")
    public ResponseResult article() {
        Subject subject = SecurityUtils.getSubject();
        //判断是否登录
        if (subject.isAuthenticated()) {
            return ResponseResult.success( "你已经登录了");
        } else {
            return ResponseResult.success("你还没有登陆");
        }
    }

    @GetMapping("setting")
    @RequiresPermissions(value = "setting")
    public ResponseResult requireAuth() {
        return ResponseResult.success("你拥有设置权限");
    }

    @GetMapping("/test")
    public ResponseResult test() {
        return ResponseResult.success("test");
    }

    @GetMapping("other")
    @RequiresPermissions(value = "other")
    public ResponseResult require() {
        return ResponseResult.success("你拥有other权限");
    }
}
