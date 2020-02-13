package com.springboot.adminmanage.controller;

import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.model.SysRoleUser;
import com.springboot.adminmanage.service.RoleUserService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * author: ouyang
 * Date:2020/2/8 20:32
 **/
@RestController
@Slf4j
@RequestMapping("/roleUser")
public class RoleUserContoller {

    @Autowired
    private RoleUserService roleUserService;

    @PostMapping("/getRoleUserByUserId")
    public Results<SysRoleUser> getRoleUserByUserId(Integer userId){
        try {
            return roleUserService.getSysRoleUserByUserId(userId);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }
}
