package com.huaxin.cloud.tms.tray.controller;

import com.huaxin.cloud.tms.tray.common.constant.Constants;
import com.huaxin.cloud.tms.tray.common.result.ResultInfo;
import com.huaxin.cloud.tms.tray.common.security.LoginUser;
import com.huaxin.cloud.tms.tray.common.security.SysLoginService;
import com.huaxin.cloud.tms.tray.common.security.SysPermissionService;
import com.huaxin.cloud.tms.tray.common.security.TokenService;
import com.huaxin.cloud.tms.tray.common.utils.ServletUtils;
import com.huaxin.cloud.tms.tray.entity.SysMenu;
import com.huaxin.cloud.tms.tray.entity.SysUser;
import com.huaxin.cloud.tms.tray.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
/**
 *
 * @Description: 登录验证
 * @author Administrator
 * @date: 2019年12月24日下午3:02:53
 */
@RestController
@CrossOrigin
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     * user：用户信息对象
     * @return 结果
     */
    @PostMapping("/login")
    public ResultInfo login(@RequestBody SysUser user)
    {
        ResultInfo ajax = ResultInfo.success();
        // 生成令牌
        String token = loginService.login(user.getUserName(), user.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public ResultInfo getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        ResultInfo ajax = ResultInfo.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public ResultInfo getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return ResultInfo.success(menuService.buildMenus(menus));
    }
}
