package com.springboot.adminmanage.common.resp;

import lombok.Getter;

@Getter
public enum MenuEnum {

    SUCCESS(1,"操作成功"),
    ERROR(0,"操作失败"),
    MENU_IS_NOT_NULL(-1,"菜单信息不能为空"),
    MENU_NAME_IS_NOT_NULL(-2,"菜单名字不能为空"),
    MENU_CODE_IS_NOT_NULL(-3,"菜单Code不能为空"),
    MENU_TYPE_IS_NOT_NULL(-4,"菜单类型不能为空"),
    MENU_IS_EXIST(-5,"菜单名称已经存在"),
    MENU_IS_NOT_EXIST(-5,"菜单名称不存在"),

    ;


    private int code;
    private String message;

    MenuEnum(int code,String message){
        this.code = code;
        this.message = message;
    }
}
