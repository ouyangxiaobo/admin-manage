package com.springboot.adminmanage.service.impl;

import com.springboot.adminmanage.common.result.ResponseCode;
import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.common.utils.EmailUtil;
import com.springboot.adminmanage.common.utils.MD5;
import com.springboot.adminmanage.common.utils.TelUtil;
import com.springboot.adminmanage.dao.SysRoleMapper;
import com.springboot.adminmanage.dao.SysRoleUserMapper;
import com.springboot.adminmanage.dao.SysUserMapper;
import com.springboot.adminmanage.model.SysRole;
import com.springboot.adminmanage.model.SysRoleUser;
import com.springboot.adminmanage.model.SysUser;
import com.springboot.adminmanage.model.dto.UserPageSearch;
import com.springboot.adminmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * author: ouyang
 * Date:2020/2/7 17:37
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * @ClassName UserServiceImpl
     * @Description : 查询所有用户<无条件>
     * @Return : Results<SysUser>
     * @Author : ouyang
     * @Date : 2020/2/13 19:10
    **/
    @Override
    public Results<SysUser> findAllUsers() {
        List<SysUser> sysUserList=sysUserMapper.queryAllUsers();
        return Results.success(sysUserList.size(), sysUserList);
    }

    /**
     * @ClassName UserServiceImpl
     * @Description : 查询所有用户<分页查询>
     * @Return : Results<SysUser>
     * @Author : ouyang
     * @Date : 2020/2/13 19:12
    **/
    @Override
    public Results<SysUser> findUsersPage(Integer pageNum,Integer pageSize) {
        Integer  count=sysUserMapper.selectUserCount();
        List<SysUser> userList=sysUserMapper.queryUsersPage(pageNum, pageSize);
        return Results.success(count,userList);
    }

    /**
     * @ClassName UserServiceImpl
     * @Description : 添加用户
     * @Return : Results<Integer>
     * @Author : ouyang
     * @Date : 2020/2/13 19:12
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> addUser(SysUser sysUser,Integer roleId) throws Exception {
        //1.判断为空
        if(StringUtils.isBlank(sysUser.getUsername())
            || StringUtils.isBlank(sysUser.getPassword())
            || StringUtils.isBlank(sysUser.getNickname())
            || sysUser.getTelephone()==null
            || StringUtils.isBlank(sysUser.getEmail())
           ){
           return Results.failure(ResponseCode.USER_INFO_IS_NOT_COMPLETE.getCode(),ResponseCode.USER_INFO_IS_NOT_COMPLETE.getMessage());
        }

        //2.判断是否同名
        SysUser user=sysUserMapper.selectUserByUserName(sysUser.getUsername());
        if(user!=null){
            return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(),ResponseCode.USERNAME_REPEAT.getMessage());
        }

        //3.判断手机格式是否正确
        if(!TelUtil.isCellPhoneNo(sysUser.getTelephone())){
            return Results.failure(ResponseCode.PHONE_IS_ERROR.getCode(),ResponseCode.PHONE_IS_ERROR.getMessage());
        }
        //4.判断手机号码是否存在
        List<SysUser> sysUserList=sysUserMapper.queryAllUsers();
        for(SysUser users: sysUserList){
            if(sysUser.getTelephone().equals(users.getTelephone())){
                return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
            }

        }
        //5.判断邮箱是否正确
        if(!EmailUtil.checkEmail(sysUser.getEmail())){
            return Results.failure(ResponseCode.EMAIL_IS_ERROR.getCode(),ResponseCode.EMAIL_IS_ERROR.getMessage());
        }

        //6.判断邮箱是否存在
        List<SysUser> userList=sysUserMapper.queryAllUsers();
        for(SysUser userEmail: userList){
            if(sysUser.getEmail().equals(userEmail.getEmail())){
                return Results.failure(ResponseCode.EMAIL_REPEAT.getCode(),ResponseCode.EMAIL_REPEAT.getMessage());
            }
        }

        //7.设置默认的sex和status
        if(sysUser.getStatus()==null){
            sysUser.setStatus(1);
        }

        //8.设置默认出生日期
        if(sysUser.getBirthday()==null){
            sysUser.setBirthday(new Date());
        }

        //9.设置默认的创建时间和更新时间
        sysUser.setCreatetime(new Date());
        sysUser.setUpdatetime(new Date());

        //10.设置加密加密
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));

        //11.添加用户
        int result=sysUserMapper.insertSelective(sysUser);

        //12.添加用户成功后。添加角色--用户
        if(result>0){
            if(roleId!=null){
                SysRoleUser sysRoleUser=new SysRoleUser();
                sysRoleUser.setRoleId(roleId);
                sysRoleUser.setUserId(sysUser.getId());
                sysRoleUserMapper.insertSelective(sysRoleUser);
                return Results.success(result);
            }else {
                return Results.failure(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage());
            }
        }
        //13.添加失败处理
        else {
            return Results.failure(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage());
        }
    }

    /**
     * @ClassName UserServiceImpl
     * @Description : 根据用户ID查询用户
     * @Return : SysUser
     * @Author : ouyang
     * @Date : 2020/2/13 19:18
    **/
    @Override
    public SysUser findUserById(Integer userId) {
        SysUser sysUser=sysUserMapper.selectByPrimaryKey(userId);
        return sysUser;
    }

    /**
     * @ClassName UserServiceImpl
     * @Description : 编辑用户信息
     * @Return :  Results<Integer>
     * @Author : ouyang
     * @Date : 2020/2/13 19:19
    **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> editUser(SysUser sysUser,Integer roleId) throws Exception {
        //1.判断为空
        if(StringUtils.isBlank(sysUser.getUsername())
                || StringUtils.isBlank(sysUser.getNickname())
                || sysUser.getTelephone()==null
                || StringUtils.isBlank(sysUser.getEmail())
        ){
            return Results.failure(ResponseCode.USER_INFO_IS_NOT_COMPLETE.getCode(),ResponseCode.USER_INFO_IS_NOT_COMPLETE.getMessage());
        }

        //2.判断手机格式是否正确
        if(!TelUtil.isCellPhoneNo(sysUser.getTelephone())){
            return Results.failure(ResponseCode.PHONE_IS_ERROR.getCode(),ResponseCode.PHONE_IS_ERROR.getMessage());
        }

        //3.判断邮箱是否正确
        if(!EmailUtil.checkEmail(sysUser.getEmail())){
            return Results.failure(ResponseCode.EMAIL_IS_ERROR.getCode(),ResponseCode.EMAIL_IS_ERROR.getMessage());
        }

        //4.设置更改对象
        SysUser userUpdate=new SysUser();
        userUpdate.setId(sysUser.getId());
        userUpdate.setSex(sysUser.getSex());
        userUpdate.setStatus(sysUser.getStatus());
        userUpdate.setPassword(sysUser.getPassword());
        userUpdate.setBirthday(sysUser.getBirthday());
        userUpdate.setEmail(sysUser.getEmail());
        userUpdate.setNickname(sysUser.getNickname());
        userUpdate.setPhone(sysUser.getPhone());
        userUpdate.setTelephone(sysUser.getTelephone());
        userUpdate.setUsername(sysUser.getUsername());
        userUpdate.setHeadimgurl(sysUser.getHeadimgurl());
        userUpdate.setCreatetime(sysUser.getCreatetime());
        userUpdate.setUpdatetime(new Date());

        //5.更改用户
        int result=sysUserMapper.updateByPrimaryKeySelective(userUpdate);

        //6.更改用户成功后。更改角色--用户
        if(result>0){
            if(roleId!=null){
                SysRoleUser roleUser=sysRoleUserMapper.selectRoleUserrByUserId(userUpdate.getId());
                SysRoleUser sysRoleUser=new SysRoleUser();
                sysRoleUser.setId(roleUser.getId());
                sysRoleUser.setRoleId(roleId);
                sysRoleUser.setUserId(userUpdate.getId());
                sysRoleUserMapper.updateByPrimaryKeySelective(sysRoleUser);
                return Results.success(result);
            }else {
                return Results.failure(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage());
            }

        }
        //7.失败处理
        else {
            return Results.failure(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage());
        }
    }

    /**
     * @ClassName UserServiceImpl
     * @Description : 删除单个用户<根据用户ID>
     * @Return : Results<Integer>
     * @Author : ouyang
     * @Date : 2020/2/13 19:21
    **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results<Integer> deleteOneUser(Integer userId) throws Exception {

        if(userId==null ||userId<=0){
            return Results.failure(ResponseCode.USER_ID_IS_NULL.getCode(),ResponseCode.USER_ID_IS_NULL.getMessage());
        }
        SysUser sysUser=sysUserMapper.selectByPrimaryKey(userId);
        if(sysUser==null){
            return Results.failure(ResponseCode.USER_IS_NOT_EXIST.getCode(),ResponseCode.USER_IS_NOT_EXIST.getMessage());
        }
        SysRoleUser roleUser=sysRoleUserMapper.selectRoleUserrByUserId(userId);
        if(roleUser!=null){

            sysRoleUserMapper.deleteByPrimaryKey(roleUser.getId());

            int result=sysUserMapper.deleteByPrimaryKey(userId);
            if(result>0){
                return Results.success(result);

            }else {
                return  Results.failure(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage());
            }
        }else {
            return Results.failure(ResponseCode.USER_ROLE_NO_CLEAR.getCode(),ResponseCode.USER_ROLE_NO_CLEAR.getMessage());
        }


    }

    /**
     * @ClassName UserServiceImpl
     * @Description : 查询用户<分页，模糊查询>
     * @Return : Results<SysUser>
     * @Author : ouyang
     * @Date : 2020/2/13 19:24
    **/
    @Override
    public Results<SysUser> findUsersPageSearch(Integer pageNum, Integer pageSize, UserPageSearch userPageSearch) {
        Integer  count=sysUserMapper.selectUserCount();
        List<SysUser> userList=sysUserMapper.queryUsersPageSearch(pageNum, pageSize,userPageSearch.getUsername(),userPageSearch.getTelephone(),userPageSearch.getNickname());
        return Results.success(count,userList);
    }

    /**
     * @ClassName UserServiceImpl
     * @Description : 根据用户名查询用户
     * @Return : SysUser
     * @Author : ouyang
     * @Date : 2020/2/13 19:25
    **/
    @Override
    public SysUser findUserByUserName(String username) {
        return sysUserMapper.selectUserByUserName(username);
    }


    /**
     * @ClassName UserServiceImpl
     * @Description : 更改用户密码
     * @Return : Results
     * @Author : ouyang
     * @Date : 2020/2/13 19:26
    **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Results updateNewPassword(String username, String oldPassword, String newPassword) throws Exception{
        SysUser sysUser=sysUserMapper.selectUserByUserName(username);
        if(sysUser==null){
            return Results.failure(ResponseCode.USER_IS_NOT_EXIST.getCode(),ResponseCode.USER_IS_NOT_EXIST.getMessage());
        }
        if(!new BCryptPasswordEncoder().encode(oldPassword).equals(sysUser.getPassword())){
            return Results.failure(ResponseCode.USER_PASSWORD_IS_ERROR.getCode(),ResponseCode.USER_PASSWORD_IS_ERROR.getMessage());
        }
        int result=sysUserMapper.upadtePassword(new BCryptPasswordEncoder().encode(newPassword),sysUser.getId());
        return Results.success(result);
    }
}
