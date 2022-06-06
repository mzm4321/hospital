package com.example.hospital.vo;

public enum ErrorCode {
    SYSTEM_ERROR(999,"系统异常"),
    NO_PATIENT(40004,"查无此患者"),
    ACCOUNT_PWD_NOT_EXIST(10002,"用户名或密码不存在"),
    TOKEN_ERROR(80001,"token异常"),
    NO_PERMISSION(70001,"无访问权限"),
    SESSION_TIME_OUT(90001,"会话超时"),
    NO_LOGIN(90002,"未登录"),
    ACCOUNT_EXIST(79998,"账户已存在~" ),
    PARAMS_ERROR(77777,"参数错误"),
    NO_USER(66666,"无此用户"),
    ERROR_PASSWORD(4321,"密码错误");

    private int code;
    private String msg;

    ErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
