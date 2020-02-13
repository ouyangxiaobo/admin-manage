package com.springboot.adminmanage.dao;

import com.springboot.adminmanage.model.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRoleUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);

    //根据userId获取roleUser对象
    SysRoleUser selectRoleUserrByUserId(Integer userId);

    //根据roleId获取user集合
    List<SysRoleUser> selectRoleUserByRoleId(Integer roleId);
}