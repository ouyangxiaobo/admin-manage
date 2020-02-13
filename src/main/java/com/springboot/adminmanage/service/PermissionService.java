package com.springboot.adminmanage.service;

import com.alibaba.fastjson.JSONArray;
import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.model.SysPermission;

public interface PermissionService {

    /*获取所有权限*/
    Results<JSONArray> listAllPermissions();

    /*获取所有权限*/
    Results<SysPermission> findAllPermissions();

    /*根据roleId获取所有权限*/
    Results<SysPermission> listAllPermissionByRoleId(Integer roleId);

    /*添加权限*/
    Results<Integer> addPermission(SysPermission sysPermission)throws Exception;

    /*编辑权限*/
    Results<Integer> editPermission(SysPermission sysPermission) throws  Exception;

    /*删除权限*/
    Results<Integer> delPermissionById(Integer id);

    /*根据userId获取权限*/
    Results<JSONArray> findMeauByUserId(Integer userId);




}
