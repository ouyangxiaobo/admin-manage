package com.springboot.adminmanage.controller;

import com.springboot.adminmanage.dao.SysPermissionMapper;
import com.springboot.adminmanage.model.SysPermission;
import com.springboot.adminmanage.model.SysRole;
import com.springboot.adminmanage.model.SysUser;
import com.springboot.adminmanage.service.RoleServcie;
import com.springboot.adminmanage.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: ouyang
 * Date:2020/2/7 17:31
 **/
@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleServcie roleServcie;


    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @RequestMapping("/user/add")
    @PreAuthorize("hasAuthority('sys:user:add')")
    @ApiOperation(value = "用户新增页面", notes = "跳转到新增用户信息页面")//描述
    public String addUser(Model model){
        model.addAttribute(new SysUser());
        return "user/user-add";
    }

    @RequestMapping("/user/edit")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @ApiOperation(value = "用户编辑页面", notes = "跳转到用户信息编辑页面")//描述
    @ApiImplicitParam(name = "user", value = "用户实体类", dataType = "SysUser")
    public String editUser(Model model,SysUser sysUser){
        model.addAttribute(userService.findUserById(sysUser.getId()));
        return "user/user-edit";
    }

    @RequestMapping("/role/add")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @ApiOperation(value = "角色添加页面", notes = "跳转到角色信息添加页面")//描述
    @ApiImplicitParam(name = "role", value = "角色实体类", dataType = "SysRole")
    public String addRole(Model model){
        model.addAttribute(new SysRole());
        return "role/role-add";
    }

    @RequestMapping("/role/edit")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @ApiOperation(value = "角色编辑页面", notes = "跳转到角色信息编辑页面")//描述
    @ApiImplicitParam(name = "role", value = "角色实体类", dataType = "SysRole")
    public String editRole(Model model,SysRole sysRole){
        model.addAttribute(roleServcie.getRoleById(sysRole.getId()));
        return "role/role-edit";
    }

    @RequestMapping("/permission/add")
    @PreAuthorize("hasAuthority('sys:menu:add')")
    @ApiOperation(value = "菜单添加页面", notes = "跳转到菜单信息添加页面")//描述
    @ApiImplicitParam(name = "sysPermission", value = "菜单实体类", dataType = "SysPermission")
    public String addPermission(Model model){
        model.addAttribute(new SysPermission());
        return "permission/permission-add";
    }

    @RequestMapping("/permission/edit")
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    @ApiOperation(value = "菜单编辑页面", notes = "跳转到菜单信息编辑页面")//描述
    @ApiImplicitParam(name = "sysPermission", value = "菜单实体类", dataType = "SysPermission")
    public String editPermission(Model model,SysPermission sysPermission){
        model.addAttribute(sysPermissionMapper.selectByPrimaryKey(sysPermission.getId()));
        return "permission/permission-add";
    }



}
