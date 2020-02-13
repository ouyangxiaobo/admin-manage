package com.springboot.adminmanage.dao;

import com.springboot.adminmanage.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    //获取所有权限
    List<SysPermission> selectAllPermissions();

    //根据roleId获取所有权限
    List<SysPermission> listAllPermissionByRoleId(Integer roleId);

    //根据parentid查询该parentid拥有的子权限
    List<SysPermission> listAllPersmissionsByParentId(Integer parentId);

    //根据parentId删除
    int deletePermissionByParentId(Integer parentId);

    //根据userId获取权限
    List<SysPermission> selectPermissionByUserId(Integer userId);

}