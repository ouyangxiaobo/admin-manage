package com.springboot.adminmanage.common.constants;

/*
 * @ClassName FileTypeConstant
 * @Desc    描述:文件类型
 * @Author  ouyang
 * @Date    2018/9/12 18:15
 */
public enum  FileTypeConstant {

    FILE_EXCEL(1),
    FILE_PDF(2),
    ;
    private Integer statusCode;

    FileTypeConstant(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public static void main(String[] args) {
        System.out.println("FILE_EXCEL".equals(FileTypeConstant.FILE_EXCEL.toString()));
    }
}
