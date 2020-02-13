package com.springboot.adminmanage.common.methodPackage;

import com.springboot.adminmanage.common.result.ResponseCode;
import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.common.utils.EmailUtil;
import com.springboot.adminmanage.common.utils.TelUtil;
import com.springboot.adminmanage.dao.SysRoleMapper;
import com.springboot.adminmanage.dao.SysRoleUserMapper;
import com.springboot.adminmanage.dao.SysUserMapper;
import com.springboot.adminmanage.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * author: ouyang
 * Date:2020/2/8 15:00
 **/
public class UserPack {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;


    public   Results<Object> checkUserInfo(SysUser sysUser){
        //判断为空
        if(StringUtils.isBlank(sysUser.getUsername())
                || StringUtils.isBlank(sysUser.getPassword())
                || StringUtils.isBlank(sysUser.getNickname())
                || sysUser.getTelephone()==null
                || StringUtils.isBlank(sysUser.getEmail())
        ){
            return Results.failure(40004, "请输入完整的用户信息：* 为必填项");
        }

        //判断是否同名

        SysUser user=sysUserMapper.selectUserByUserName(sysUser.getUsername());
        if(user!=null){
            return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(),ResponseCode.USERNAME_REPEAT.getMessage());
        }

        //判断手机格式是否正确
        if(!TelUtil.isCellPhoneNo(sysUser.getTelephone())){
            return Results.failure(ResponseCode.PHONE_IS_ERROR.getCode(),ResponseCode.PHONE_IS_ERROR.getMessage());
        }
        //判断手机号码是否存在
        List<SysUser> sysUserList=sysUserMapper.queryAllUsers();
        for(SysUser users: sysUserList){
            if(sysUser.getTelephone().equals(users.getTelephone())){
                return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
            }

        }
        //判断邮箱是否正确
        if(!EmailUtil.checkEmail(sysUser.getEmail())){
            return Results.failure(ResponseCode.EMAIL_IS_ERROR.getCode(),ResponseCode.EMAIL_IS_ERROR.getMessage());
        }

        //判断邮箱是否存在
        List<SysUser> userList=sysUserMapper.queryAllUsers();
        for(SysUser userEmail: userList){
            if(sysUser.getEmail().equals(userEmail.getEmail())){
                return Results.failure(ResponseCode.EMAIL_REPEAT.getCode(),ResponseCode.EMAIL_REPEAT.getMessage());
            }

        }

        return Results.success();
    }
}
