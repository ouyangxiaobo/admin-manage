package com.springboot.adminmanage.controller;

import com.springboot.adminmanage.common.result.PageResult;
import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.model.SysRole;
import com.springboot.adminmanage.model.dto.RoleDto;
import com.springboot.adminmanage.service.RoleServcie;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author: ouyang
 * Date:2020/2/8 12:32
 **/
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {


    @Autowired
    private RoleServcie roleServcie;

    @GetMapping("/getAllRoles")
    public Results<SysRole> getAllRoles(){
        try {

            Results<SysRole> results=roleServcie.queryAllRoleList();
            List<SysRole> sysRoleList=results.getDatas();
            for (SysRole sysRole : sysRoleList){
                log.info("角色列表="+sysRole);
            }
            return results;


        }catch (Exception e){
            e.printStackTrace();
            log.error("角色列表错误="+e);
        }
        return null;
    }


    @PostMapping("/findRolesPage")
    @PreAuthorize("hasAuthority('sys:role:query')")
    @ApiOperation(value = "分页获取角色", notes = "用户分页获取角色信息")//描述
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "limit", required = true,dataType = "Integer"),
    })
    public Results<SysRole> findRolesPage(PageResult pageResult){
        try {

            log.info("页数=="+pageResult.getPage());
            log.info("每页多少=="+pageResult.getLimit());
            log.info("默认===="+pageResult.getOffset());
            return roleServcie.queryRolesPage(pageResult.getOffset(),pageResult.getLimit());

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    @PostMapping("/findRolesPageSearch")
    @PreAuthorize("hasAuthority('sys:role:query')")
    @ApiOperation(value = "分页模糊查询获取角色", notes = "用户分页模糊查询获取角色信息")//描述
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "limit", required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "name", required = true,dataType = "String"),
    })
    public Results<SysRole> findRolesPageSearch(PageResult pageResult,String name){
        try {

            log.info("页数=="+pageResult.getPage());
            log.info("每页多少=="+pageResult.getLimit());
            log.info("默认===="+pageResult.getOffset());
            log.info("角色名="+name);
            return roleServcie.findRolesPageSearch(pageResult.getOffset(),pageResult.getLimit(),name);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/addRole")
    @PreAuthorize("hasAuthority('sys:role:add')")
    @ApiOperation(value = "保存角色信息", notes = "保存新增的角色信息")//描述
    @ApiImplicitParam(name = "roleDto",value = "角色信息实体类", required = true,dataType = "RoleDto")
    public Results<Integer> addRole(@RequestBody RoleDto roleDto){
        try {
            log.info("角色名=="+roleDto.getName());
            log.info("描述=="+roleDto.getDescription());

            return roleServcie.addRole(roleDto);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/getRoleById")
    public SysRole getRoleById(Integer roleId){
        try {
            log.info("角色ID=="+roleId);
            ;

            return roleServcie.getRoleById(roleId);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/editRole")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @ApiOperation(value = "保存角色信息", notes = "保存被编辑的角色信息")//描述
    @ApiImplicitParam(name = "roleDto",value = "角色信息实体类", required = true,dataType = "RoleDto")
    public Results<Integer> editRole(@RequestBody RoleDto roleDto){
        try {
            log.info("角色=="+roleDto.toString());

            log.info("角色ID=="+roleDto.getId());
            ;

            return roleServcie.editRole(roleDto);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/delRole")
    @PreAuthorize("hasAuthority('sys:role:del')")
    @ApiOperation(value = "删除角色信息", notes = "删除角色信息")//描述
    public Results<Integer> delRole(Integer roleId){
        try {
            log.info("角色ID=="+roleId);



            return roleServcie.delOneRole(roleId);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }



}
