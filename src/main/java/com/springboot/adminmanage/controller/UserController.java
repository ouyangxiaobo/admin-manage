package com.springboot.adminmanage.controller;

import com.springboot.adminmanage.common.result.PageResult;
import com.springboot.adminmanage.common.result.Results;
import com.springboot.adminmanage.model.SysUser;
import com.springboot.adminmanage.model.dto.UserPageSearch;
import com.springboot.adminmanage.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: ouyang
 * Date:2020/2/7 17:40
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    String pattern="yyyy-MM-dd";
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest webRequest){
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat(pattern), true));

    }

    @GetMapping("/findAllUsers")
    public Results<SysUser> findAllUser(){
        try {
            return  userService.findAllUsers();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    @PostMapping("/findUsersPage")
    @ApiOperation(value = "分页获取用户信息", notes = "分页获取用户信息")//描述
    @ApiImplicitParam(name = "request", value = "分页查询实体类", required=false)
    @PreAuthorize("hasAuthority('sys:user:query')")
    public Results<SysUser> findUsersPage(PageResult pageResult){
        try {
            log.info("页数=="+pageResult.getPage());
            log.info("每页多少=="+pageResult.getLimit());
            log.info("默认===="+pageResult.getOffset());

             pageResult.countPage();
             return userService.findUsersPage(pageResult.getOffset(),pageResult.getLimit());

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('sys:user:add')")
    @ApiOperation(value = "保存用户信息", notes = "保存新增的用户信息")//描述
    public Results<Integer> addUser(SysUser sysUser,Integer roleId){
        try {
            log.info("添加用户=="+sysUser.toString());

            return userService.addUser(sysUser,roleId);

        }catch (Exception e){
            e.printStackTrace();
            log.error("添加用户时的错误信息="+e.getMessage());
        }

        return null;
    }


    @PostMapping("/getUserById")
    public SysUser getUserById(Integer userId){
        try {
            log.info("用户ID=="+userId);
            return userService.findUserById(userId);

        }catch (Exception e){
            e.printStackTrace();
            log.error("根据Id获取单个用户时的错误信息="+e.getMessage());
        }
        return null;
    }

    @PostMapping("/editUser")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @ApiOperation(value = "编辑用户信息", notes = "保存编辑的用户信息")//描述
    public Results<Integer> editUser(SysUser sysUser,Integer roleId){
        try {
            log.info("更改用户=="+sysUser.toString());

            return userService.editUser(sysUser,roleId);

        }catch (Exception e){
            e.printStackTrace();
            log.error("更改用户时的错误信息="+e.getMessage());
        }

        return null;
    }

    @PostMapping("/delUser")
    @PreAuthorize("hasAuthority('sys:user:del')")
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息")//描述
    @ApiImplicitParam(name = "sysUser", value = "用户实体类", required = true, dataType = "userId")
    public Results<Integer> delUser(Integer userId){
        try {
            log.info("删除单个用户Id=="+userId);

            return userService.deleteOneUser(userId);

        }catch (Exception e){
            e.printStackTrace();
            log.error("删除单个用户Id的错误信息="+e.getMessage());
        }

        return null;
    }

    @PostMapping("/findUsersPageSearch")
    @PreAuthorize("hasAuthority('sys:user:query')")
    @ApiOperation(value = "模糊查询用户信息", notes = "模糊搜索查询用户信息")//描述
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "模糊搜索的用户名", required = true),
            @ApiImplicitParam(name = "telephone",value = "模糊搜索的电话", required = true),
            @ApiImplicitParam(name = "nickname",value = "模糊搜索的昵称", required = true),
    })
    public Results<SysUser> findUsersPageSearch(PageResult pageResult, UserPageSearch userPageSearch){
        try {
            log.info("页数=="+pageResult.getPage());
            log.info("每页多少=="+pageResult.getLimit());
            log.info("默认===="+pageResult.getOffset());

            pageResult.countPage();
            return userService.findUsersPageSearch(pageResult.getOffset(),pageResult.getLimit(),userPageSearch);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/changePassword")
    public Results changePassword(String username,String oldPassword,String newPassword){
        try {
            log.info("【修改密码】--用户名："+username);
            log.info("【修改密码】--旧密码："+oldPassword);
            log.info("【修改密码】--新密码："+newPassword);

            return userService.updateNewPassword(username,oldPassword ,newPassword);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
