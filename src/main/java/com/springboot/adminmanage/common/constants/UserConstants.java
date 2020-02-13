package com.springboot.adminmanage.common.constants;

public enum  UserConstants {

    /**
     *
     * 账户是否停用
     * ‘1’= 是；
     * ‘2’= 不是；
     *
     */

    ISBLOCK(1,"是"),
    NOTBLOCk(2,"否");



    Integer statusCode;
    String statusStr;
    UserConstants(Integer statusCode,String statusStr){
        this.statusStr = statusStr;
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusStr() {
        return statusStr;
    }


    /**
     *
     * @param statusCode
     * @return
     */
    public static String getStatusStr (Integer statusCode) {
        for (UserConstants userConstants : UserConstants.values()) {
            if (userConstants.getStatusCode().equals(statusCode)) {
                return userConstants.getStatusStr();
            }
        }
        return "";
    }
}
