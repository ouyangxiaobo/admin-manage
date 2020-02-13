package com.springboot.adminmanage.service;

import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.model.SysRole;
import com.springboot.adminmanage.model.SysUser;
import com.springboot.adminmanage.model.dto.RoleDto;
import com.springboot.adminmanage.model.dto.UserPageSearch;

public interface RoleServcie {

    /*查询所有角色*/
    Results<SysRole> queryAllRoleList();

    /*角色分页*/
    Results<SysRole> queryRolesPage(Integer pageNum,Integer pageSize);

    /*分页搜索*/
    Results<SysRole> findRolesPageSearch(Integer pageNum, Integer pageSize, String name);

    /*添加角色*/
    Results<Integer> addRole(RoleDto roleDto) throws Exception;

    /*修改角色*/
    Results<Integer> editRole(RoleDto roleDto)throws  Exception;


    /*根据Id获取角色*/
    SysRole getRoleById(Integer roleId);

    /*删除单个角色*/
    Results<Integer> delOneRole(Integer roleId) throws  Exception;
}
