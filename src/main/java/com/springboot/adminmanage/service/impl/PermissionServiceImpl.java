package com.springboot.adminmanage.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.springboot.adminmanage.common.result.ResponseCode;
import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.common.utils.TreeUtils;
import com.springboot.adminmanage.dao.SysPermissionMapper;
import com.springboot.adminmanage.dao.SysRolePermissionMapper;
import com.springboot.adminmanage.model.SysPermission;
import com.springboot.adminmanage.model.SysRolePermission;
import com.springboot.adminmanage.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author: ouyang
 * Date:2020/2/10 14:52
 **/
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    /**
     * @ClassName PermissionServiceImpl
     * @Description : 查询所有权限并且转为JSONArray
     * @Return : Results<JSONArray>
     * @Author : ouyang
     * @Date : 2020/2/13 19:36
    **/
    @Override
    public Results<JSONArray> listAllPermissions() {
        List<SysPermission> sysPermissionList=sysPermissionMapper.selectAllPermissions();
        JSONArray jsonArray=new JSONArray();
        TreeUtils.setPermissionsTree(0, sysPermissionList, jsonArray);
        return Results.success(jsonArray);
    }

    /**
     * @ClassName PermissionServiceImpl
     * @Description : 根据角色ID查询权限集合
     * @Return : Results<SysPermission>
     * @Author : ouyang
     * @Date : 2020/2/13 19:37
    **/
    @Override
    public Results<SysPermission> listAllPermissionByRoleId(Integer roleId) {
        if(roleId==null || roleId==0){
            return Results.failure(ResponseCode.ROLE_ID_IS_NULL.getCode(),ResponseCode.ROLE_ID_IS_NULL.getMessage());
        }
        List<SysPermission> sysPermissionList=sysPermissionMapper.listAllPermissionByRoleId(roleId);
        return Results.success(sysPermissionList.size(),sysPermissionList);
    }

    /**
     * @ClassName PermissionServiceImpl
     * @Description : 查询所有角色<无条件>
     * @Return : Results<SysPermission>
     * @Author : ouyang
     * @Date : 2020/2/13 19:37
    **/
    @Override
    public Results<SysPermission> findAllPermissions() {
        List<SysPermission> sysPermissionList=sysPermissionMapper.selectAllPermissions();
        return Results.success(sysPermissionList.size(),sysPermissionList);
    }

    /**
     * @ClassName PermissionServiceImpl
     * @Description : 添加权限
     * @Return : Results<Integer>
     * @Author : ouyang
     * @Date : 2020/2/13 19:38
    **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> addPermission(SysPermission sysPermission) throws Exception {
        if(sysPermission==null){
            return Results.failure();
        }
        int result=sysPermissionMapper.insertSelective(sysPermission);
        if(result>0){
            return Results.success(result);
        }else {
            return Results.failure();
        }

    }

    /**
     * @ClassName PermissionServiceImpl
     * @Description : 编辑权限
     * @Return : Results<Integer>
     * @Author : ouyang
     * @Date : 2020/2/13 19:38
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> editPermission(SysPermission sysPermission) throws Exception {
        if(sysPermission==null){
            return Results.failure();
        }
        int result=sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
        if(result>0){
            return Results.success(result);
        }else {
            return Results.failure();
        }
    }

    /**
     * @ClassName PermissionServiceImpl
     * @Description : 删除权限
     * @Return : Results<Integer>
     * @Author : ouyang
     * @Date : 2020/2/13 19:38
    **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> delPermissionById(Integer id) {
        int result=sysPermissionMapper.deleteByPrimaryKey(id);
        sysPermissionMapper.deletePermissionByParentId(id);
        if(result>0){
            return  Results.success(result);
        }else {
           return Results.failure();
        }

    }

    /**
     * @ClassName PermissionServiceImpl
     * @Description : 根据用户ID查询用户拥有的权限<转为JSONArray>
     * @Return : Results<JSONArray>
     * @Author : ouyang
     * @Date : 2020/2/13 19:39
    **/
    @Override
    public Results<JSONArray> findMeauByUserId(Integer userId) {
        List<SysPermission> sysPermissionList=sysPermissionMapper.selectPermissionByUserId(userId);
        sysPermissionList=sysPermissionList.stream().filter(p->p.getType().equals(1)).collect(Collectors.toList());
        JSONArray jsonArray=new JSONArray();
        TreeUtils.setPermissionsTree(0, sysPermissionList, jsonArray);
        return Results.success(jsonArray);

    }
}
