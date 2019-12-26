package com.huaxin.cloud.tms.tray.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huaxin.cloud.tms.tray.common.annotation.Log;
import com.huaxin.cloud.tms.tray.common.constant.UserConstants;
import com.huaxin.cloud.tms.tray.common.controller.BaseController;
import com.huaxin.cloud.tms.tray.common.enums.BusinessType;
import com.huaxin.cloud.tms.tray.common.result.ResultInfo;
import com.huaxin.cloud.tms.tray.common.utils.SecurityUtils;
import com.huaxin.cloud.tms.tray.entity.SysMenu;
import com.huaxin.cloud.tms.tray.service.SysMenuService;
/**
 * 
 * @Description:菜单管理 
 * @author Administrator
 * @date: 2019年12月24日下午3:01:58
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController
{
    @Autowired
    private SysMenuService menuService;

    /**
     * 获取菜单列表
     */
//    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    @PostMapping("/list")
    public ResultInfo list(SysMenu menu)
    {
        List<SysMenu> menus = menuService.selectMenuList(menu);
        return ResultInfo.success(menuService.buildMenuTree(menus));
    }

    /**
     * 根据菜单编号获取详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:menu:query')")
    @GetMapping(value = "/{menuId}")
    public ResultInfo getInfo(@PathVariable Long menuId)
    {
        return ResultInfo.success(menuService.selectMenuById(menuId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public ResultInfo treeselect(SysMenu dept)
    {
        List<SysMenu> menus = menuService.selectMenuList(dept);
        return ResultInfo.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public ResultInfo roleMenuTreeselect(@PathVariable("roleId") Long roleId)
    {
        return ResultInfo.success(menuService.selectMenuListByRoleId(roleId));
    }

    /**
     * 新增菜单
     */
//    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultInfo add(@Validated @RequestBody SysMenu menu)
    {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu)))
        {
            return ResultInfo.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setCreateBy(SecurityUtils.getUsername());
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
//    @PreAuthorize("@ss.hasPermi('system:menu:edit')")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultInfo edit(@Validated @RequestBody SysMenu menu)
    {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu)))
        {
            return ResultInfo.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public ResultInfo remove(@PathVariable("menuId") Long menuId)
    {
        if (menuService.hasChildByMenuId(menuId))
        {
            return ResultInfo.error("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId))
        {
            return ResultInfo.error("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }
}