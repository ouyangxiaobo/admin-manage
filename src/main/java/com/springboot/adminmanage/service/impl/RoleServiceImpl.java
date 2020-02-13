package com.springboot.adminmanage.service.impl;

import com.springboot.adminmanage.common.result.ResponseCode;
import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.dao.SysPermissionMapper;
import com.springboot.adminmanage.dao.SysRoleMapper;
import com.springboot.adminmanage.dao.SysRolePermissionMapper;
import com.springboot.adminmanage.dao.SysRoleUserMapper;
import com.springboot.adminmanage.model.SysPermission;
import com.springboot.adminmanage.model.SysRole;
import com.springboot.adminmanage.model.SysRolePermission;
import com.springboot.adminmanage.model.SysRoleUser;
import com.springboot.adminmanage.model.dto.RoleDto;
import com.springboot.adminmanage.service.RoleServcie;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * author: ouyang
 * Date:2020/2/8 12:30
 **/

@Service
public class RoleServiceImpl implements RoleServcie {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;


    /**
     * @ClassName RoleServiceImpl
     * @Description : 查询所有角色
     * @Return : Results<SysRole>
     * @Author : ouyang
     * @Date : 2020/2/13 19:30
    **/
    @Override
    public Results<SysRole> queryAllRoleList() {
        List<SysRole> sysRoleList=sysRoleMapper.selectAllRoles();
        return Results.success(sysRoleList.size(),sysRoleList);
    }

    /**
     * @ClassName RoleServiceImpl
     * @Description : 分页查询所有角色
     * @Return : Results<SysRole>
     * @Author : ouyang
     * @Date : 2020/2/13 19:31
    **/
    @Override
    public Results<SysRole> queryRolesPage(Integer pageNum, Integer pageSize) {
        Integer count=sysRoleMapper.selectRoleCounts();
        if(pageNum==null){
            pageNum=0;
        }

        List<SysRole> sysRoleList=sysRoleMapper.queryRolesPage(pageNum,pageSize);
        return Results.success(count,sysRoleList);
    }

    /**
     * @ClassName RoleServiceImpl
     * @Description : 分页，模糊查询所有角色
     * @Return : Results<SysRole>
     * @Author : ouyang
     * @Date : 2020/2/13 19:32
    **/
    @Override
    public Results<SysRole> findRolesPageSearch(Integer pageNum, Integer pageSize, String name) {

        Integer count=sysRoleMapper.selectRoleCounts();
        if(pageNum==null){
            pageNum=0;
        }

        List<SysRole> sysRoleList=sysRoleMapper.queryRolesPageSearch(pageNum,pageSize,name);
        return Results.success(count,sysRoleList);

    }

    /**
     * @ClassName RoleServiceImpl
     * @Description : 添加角色
     * @Return : Results<Integer>
     * @Author : ouyang
     * @Date : 2020/2/13 19:32
    **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> addRole(RoleDto roleDto) throws Exception {
        if(roleDto==null){
            return Results.failure(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage());
        }
        SysRole role=sysRoleMapper.selectOneRoleByRoleName(roleDto.getName());
        if(role!=null){
            return Results.failure(ResponseCode.ROLE_NAME_IS_EXIST.getCode(),ResponseCode.ROLE_NAME_IS_EXIST.getMessage());
        }

        roleDto.setCreatetime(new Date());
        roleDto.setUpdatetime(new Date());
        int result=sysRoleMapper.insertSelective(roleDto);
        if(result>0){
            List<Long> permissionIds = roleDto.getPermissionIds();
            //1.移除0,permission id是从1开始
            permissionIds.remove(0L);
            //2、保存角色对应的所有权限
            if (!CollectionUtils.isEmpty(permissionIds)) {
                sysRolePermissionMapper.saveRolePermissionList(roleDto.getId(), permissionIds);
            }

            return Results.success(result);
        }else {
            return Results.failure(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage());
        }

    }

    /**
     * @ClassName RoleServiceImpl
     * @Description : 根据角色ID获取角色
     * @Return : SysRole
     * @Author : ouyang
     * @Date : 2020/2/13 19:33
    **/
    @Override
    public SysRole getRoleById(Integer roleId) {
        return sysRoleMapper.selectByPrimaryKey(roleId);
    }

    /**
     * @ClassName RoleServiceImpl
     * @Description : 编辑角色
     * @Return : Results<Integer>
     * @Author : ouyang
     * @Date : 2020/2/13 19:33
    **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> editRole(RoleDto roleDto) throws Exception {
        List<Long> permissionIds = roleDto.getPermissionIds();
        //1.移除0,permission id是从1开始
        permissionIds.remove(0L);
        //2.删除原有的权限
        sysRolePermissionMapper.deleteByRoleId(roleDto.getId());

        //3.保存角色对应的所有权限
        if (!CollectionUtils.isEmpty(permissionIds)) {
            sysRolePermissionMapper.saveRolePermissionList(roleDto.getId(), permissionIds);
        }
        int result=sysRoleMapper.updateByPrimaryKeySelective(roleDto);
        if(result>0){
            return Results.success(result);
        }else {
            return Results.failure(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage());
        }

    }

    /**
     * @ClassName RoleServiceImpl
     * @Description : 删除角色
     * @Return : Results<Integer>
     * @Author : ouyang
     * @Date : 2020/2/13 19:34
    **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> delOneRole(Integer roleId) throws Exception {
        if(roleId==null || roleId<=0){
            return Results.failure(ResponseCode.ROLE_ID_IS_NULL.getCode(),ResponseCode.ROLE_ID_IS_NULL.getMessage());
        }
        List<SysRoleUser> sysRoleUserList=sysRoleUserMapper.selectRoleUserByRoleId(roleId);
        if(sysRoleUserList.size()>0){
            return Results.failure(ResponseCode.USER_ROLE_NO_CLEAR.getCode(),ResponseCode.USER_ROLE_NO_CLEAR.getMessage());
        }

        int result=sysRoleMapper.deleteByPrimaryKey(roleId);

        return Results.success(result);

    }


}
