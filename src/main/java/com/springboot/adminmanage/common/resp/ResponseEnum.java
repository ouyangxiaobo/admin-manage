package com.springboot.adminmanage.common.resp;

import lombok.Getter;


@Getter
public enum ResponseEnum {
    SUCCESS(1,"操作成功"),
    ERROR(0,"操作失败"),;


    private int code;
    private String message;

    ResponseEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
