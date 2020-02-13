package com.springboot.adminmanage.dao;

import com.springboot.adminmanage.model.SysRole;
import com.springboot.adminmanage.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    //通过角色名查询角色
    SysRole selectOneRoleByRoleName(String roleName);

    //获取角色的个数
    Integer selectRoleCounts();

    //获取所有角色
    List<SysRole> selectAllRoles();

    //角色分页
    List<SysRole> queryRolesPage(@Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize);

    //角色分页模糊查询
    List<SysRole> queryRolesPageSearch(@Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize,@Param("name") String name);
}