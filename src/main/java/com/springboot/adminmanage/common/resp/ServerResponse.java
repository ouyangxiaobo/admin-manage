package com.springboot.adminmanage.common.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.io.Serializable;


@Getter
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    public ServerResponse(){}

    public ServerResponse(int status){
        this.status = status;
    }
    public ServerResponse(int status,String msg){
        this.status = status;
        this.msg = msg;
    }
    public ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }
    public ServerResponse(int status,String msg,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseEnum.SUCCESS.getCode();
    }

    /**
     * 成功的方法
     */
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage());
    }
    public static <T> ServerResponse<T> createBySuccessMessage(String message){
        return new ServerResponse<>(ResponseEnum.SUCCESS.getCode(),message);
    }
    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<>(ResponseEnum.SUCCESS.getCode(),data);
    }
    public static <T> ServerResponse<T> createBySuccess(String message, T data){
        return new ServerResponse<>(ResponseEnum.SUCCESS.getCode(),message,data);
    }

    /**
     * 失败的方法
     */
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<>(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getMessage());
    }
    public static <T> ServerResponse<T> createByErrorMessage(String msg){
        return new ServerResponse<>(ResponseEnum.ERROR.getCode(),msg);
    }
    public static <T> ServerResponse<T> createByErrorCodeMessage(int code, String msg){
        return new ServerResponse<>(code,msg);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
