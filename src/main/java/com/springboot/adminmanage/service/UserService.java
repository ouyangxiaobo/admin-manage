package com.springboot.adminmanage.service;

import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.model.SysUser;
import com.springboot.adminmanage.model.dto.UserPageSearch;

import java.util.List;

public interface UserService {

    /*查询所有用户*/
    Results<SysUser> findAllUsers();

    /*查询用户分页*/
    Results<SysUser> findUsersPage(Integer pageNum,Integer pageSize);

    /*根据username获取user*/
    SysUser findUserByUserName(String username);

    /*添加用户*/
    Results<Integer> addUser(SysUser sysUser,Integer roleId)throws  Exception;

    /*根据Id获取用户对象*/
    SysUser findUserById(Integer userId);

    /*用户更改*/
    Results<Integer> editUser(SysUser sysUser,Integer roleId) throws  Exception;

    /*删除单个用户*/
    Results<Integer> deleteOneUser(Integer userId) throws  Exception;

    /*分页搜索*/
    Results<SysUser> findUsersPageSearch(Integer pageNum, Integer pageSize, UserPageSearch userPageSearch);

    /*修改密码*/
    Results updateNewPassword(String username,String oldPassword,String newPassword) throws Exception;
}
