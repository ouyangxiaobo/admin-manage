package com.springboot.adminmanage.dao;

import com.springboot.adminmanage.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    //根据用户名查询用户
    SysUser selectUserByUserName(String username);

    //查询所有用户
    List<SysUser> queryAllUsers();



    //查询并分页
    List<SysUser> queryUsersPage(@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);

    //查询用户数量
    Integer selectUserCount();

    //分页搜索

    //查询并分页
    List<SysUser> queryUsersPageSearch(@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize,@Param("username") String username,@Param("telephone") String telephone

    ,@Param("nickname") String nickname
    );

      //修改密码
    @Update("update sys_user u set u.password=#{password} where u.id=#{userId}")
    int upadtePassword(@Param("password") String password,@Param("userId") Integer userId);
}