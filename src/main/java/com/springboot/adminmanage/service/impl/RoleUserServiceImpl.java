package com.springboot.adminmanage.service.impl;

import com.springboot.adminmanage.common.result.ResponseCode;
import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.dao.SysRoleUserMapper;
import com.springboot.adminmanage.model.SysRoleUser;
import com.springboot.adminmanage.service.RoleUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: ouyang
 * Date:2020/2/8 20:29
 **/
@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    /**
     * @ClassName RoleUserServiceImpl
     * @Description : 根据用户ID查询用户-角色
     * @Return : Results<SysRoleUser>
     * @Author : ouyang
     * @Date : 2020/2/13 19:40
    **/
    @Override
    public Results<SysRoleUser> getSysRoleUserByUserId(Integer userId) {
        SysRoleUser sysRoleUser=sysRoleUserMapper.selectRoleUserrByUserId(userId);
        return Results.success(sysRoleUser);
    }
}
