package com.springboot.book.common.resp;

import lombok.Getter;

/*
 * @ClassName
 * @Decription TOO
 * @Author HanniOvO
 * @Date 2019/6/21 18:34
 */
@Getter
public enum UserEnum {

    SUCCESS(1,"操作成功"),
    ERROR(0,"操作失败"),
    USER_IS_NULL(-1,"用户信息为空"),
    USER_NAME_IS_NOT_NULL(-2,"用户名不能为空"),
    USER_PASSWORD_IS_NOT_NULL(-3,"用户密码不能为空"),
    USER_PHONE_IS_NOT_NULL(-4,"用户手机号码不能为空"),
    USER_PHONE_IS_NOT_RIGHT(-5,"请输入正确的手机号码"),
    USER_EMAIL_IS_NOT_NULL(-6,"用户邮箱不能为空"),
    USER_EMAIL_IS_NOT_RIGHT(-7,"请输入正确的邮箱"),
    USER_NAME_IS_EXIST(-8,"用户名已经存在"),
    USER_ID_IS_NOT_NULL(-9,"请选择要删除的用户"),
    USER_IS_NOT_EXIST(-10,"用户不存在"),
    USER_ID_IS_NULL(-11,"用户ID为空"),
    ;


    private int code;
    private String message;

    UserEnum(int code,String message){
        this.code = code;
        this.message = message;
    }
}
