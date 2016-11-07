package me.jcala.xmarket.conf;

public enum Api {
    //普通操作相关
    SUCCESS(100,"操作成功"),
    SERVER_ERROR(101,"服务器异常"),
    FORBIDDEN(102,"没有权限"),
    ILLEGAL_PARAMS(103,"请求参数不合法"),
    TOKEN_ILLEGAL(105,"token不合法"),
    //用户相关
    USER_NOT_EXIST(201,"用户不存在"),
    USER_PASS_ERR(202,"密码错误"),
    USER_NAME_EXIST(203,"该用户名已经存在"),
    USER_PHONE_EXIST(204,"该手机号已经被注册"),
    USER_OLD_PASS_ERR(205,"原密码错误"),
    //图片相关
    PIC_NOT_EXIST(301,"该图片资源不存在");
    private int code;
    private String msg;

    Api(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }
}
