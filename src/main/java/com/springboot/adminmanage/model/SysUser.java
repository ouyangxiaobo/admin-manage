package com.springboot.adminmanage.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String headimgurl;

    private String phone;

    private String telephone;

    private String email;

    private Date birthday;

    private Integer sex;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    public interface Status {
        int DISABLED = 0;
        int VALID = 1;
        int LOCKED = 2;
    }


}