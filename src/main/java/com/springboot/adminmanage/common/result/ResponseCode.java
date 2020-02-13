package com.springboot.adminmanage.common.result;

public enum ResponseCode {

	// 公共请求信息
	SUCCESS(200, "请求成功"),
	TABLE_SUCCESS(0, "请求成功"),
	FAIL(500, "请求失败，请联系后台管理员"),
	PARAMETER_MISSING(600,"参数缺失"),
	UNAUTHORIZED(401,"未授权"),
	// ..一真往后面加

	//用户信息
	//5000100 - 5000200
	USER_PASSWORD_IS_ERROR(5000099,"用户新旧密码不一致"),
	USERNAME_REPEAT(5000100,"用户名已存在"),
	PHONE_REPEAT(5000101,"手机号已存在"),
	EMAIL_REPEAT(5000102,"邮箱已存在"),
	PHONE_IS_ERROR(5000103,"手机格式不正确"),
	EMAIL_IS_ERROR(5000104,"邮箱格式不正确"),
	USER_INFO_IS_NOT_COMPLETE(5000105,"请输入完整的用户信息：* 为必填项"),
	USER_ID_IS_NULL(5000106,"用户ID查询不到"),
	USER_IS_NOT_EXIST(5000107,"用户不存在"),

	//用户-角色
	//5000201 - 5000300
	USER_ROLE_NO_CLEAR(5000201,"该角色存在用户关联，无法删除"),

	//角色
	ROLE_ID_IS_NULL(5000108,"角色ID找不到"),
	ROLE_NAME_IS_EXIST(5000109,"角色名已存在"),

    //角色-权限
	ROLE_PERMISSION_IS_NULL(5000110,"该角色没有设置权限，请联系管理员"),

	//权限
	PERMISSION_ID_IS_NULL(5000111,"权限ID不存在或者不合法"),
	PERMISSION_IS_NULL(5000112,"权限不存在"),
	;
	private Integer code;
	
	private String message;
	
	ResponseCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static String getMessage(String name) {
		for (ResponseCode item : ResponseCode.values()) {
			if (item.name().equals(name)) {
				return item.message;
			}
		}
		return null;
	}

	public static Integer getCode(String name) {
		for (ResponseCode item : ResponseCode.values()) {
			if (item.name().equals(name)) {
				return item.code;
			}
		}
		return null;
	}
}
