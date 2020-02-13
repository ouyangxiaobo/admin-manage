package com.springboot.adminmanage.service;

import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.model.SysRoleUser;

public interface RoleUserService {

    /*根据userId获取SysRoleUser对象*/
    Results<SysRoleUser> getSysRoleUserByUserId(Integer userId);
}
