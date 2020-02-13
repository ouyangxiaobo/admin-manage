package com.springboot.adminmanage.dao;

import com.springboot.adminmanage.model.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

    int saveRolePermissionList(@Param("roleId")Integer id, @Param("permissionIds") List<Long> permissionIds);

    //根据roleId删除role-permission
    int deleteByRoleId(Integer roleId);

    //根据roleId查询role-permission的数量
    int selectRolePermissionCount(Integer roleId);

    //根据permissionId查询role-permission
    List<SysRolePermission> listRolePermissionsByPermissionId(Integer permissionId);
}