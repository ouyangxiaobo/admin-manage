package com.springboot.adminmanage.controller;

import com.alibaba.fastjson.JSONArray;
import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.model.SysPermission;
import com.springboot.adminmanage.model.dto.RoleDto;
import com.springboot.adminmanage.service.PermissionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: ouyang
 * Date:2020/2/10 14:58
 **/
@RestController
@RequestMapping("/permission")
@Slf4j
public class PermissionControllerr {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/listAllPermission")
    @PreAuthorize("hasAuthority('sys:menu:query')")
    @ApiOperation(value = "获取所有权限值", notes = "获取所有菜单的权限值")//描述
    public Results<JSONArray> listAllPermission(){
        try {

            return permissionService.listAllPermissions();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/menuAll")
    @PreAuthorize("hasAuthority('sys:menu:query')")
    @ApiOperation(value = "获取所有权限值", notes = "获取所有菜单的权限值")//描述
    public Results<SysPermission> menuAll(){
        try {

            return permissionService.findAllPermissions();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    @GetMapping("/listAllPermissionByRoleId")
    public Results<SysPermission> listAllPermissionByRoleId(RoleDto roleDto){
        try {
            log.info("角色Id=="+roleDto.getId());

            return permissionService.listAllPermissionByRoleId(roleDto.getId());

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:menu:add')")
    @ApiOperation(value = "添加菜单", notes = "保存用户新增的菜单信息")//描述
    @ApiImplicitParam(name = "sysPermission", value = "菜单权限实体sysPermission", required = true, dataType = "SysPermission")
    public Results<Integer> addPermission(@RequestBody SysPermission sysPermission){
        try {
            log.info("sysPermission=="+sysPermission.toString());

            return permissionService.addPermission(sysPermission);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    @ApiOperation(value = "更新菜单信息", notes = "保存用户编辑完的菜单信息")//描述
    @ApiImplicitParam(name = "sysPermission", value = "菜单权限实体sysPermission", required = true, dataType = "SysPermission")
    public Results<Integer> editPermission(@RequestBody SysPermission sysPermission){
        try {
            log.info("sysPermission=="+sysPermission.toString());

            return permissionService.editPermission(sysPermission);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/delPermission")
    @PreAuthorize("hasAuthority('sys:menu:del')")
    @ApiOperation(value = "删除菜单", notes = "根据菜单Id去删除菜单")//描述
    public Results<Integer> delPermission(Integer id){
        try {
            log.info("权限id=="+id);

            return permissionService.delPermissionById(id);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/getMenu")
    @ApiOperation(value = "获取菜单", notes = "获取用户所属角色下能显示的菜单")//描述
    @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "Integer")
    public Results<JSONArray> getMenu(Integer userId){
        try {

            return permissionService.findMeauByUserId(userId);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
