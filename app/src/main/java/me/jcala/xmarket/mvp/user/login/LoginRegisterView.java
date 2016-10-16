package me.jcala.xmarket.mvp.user.login;

public interface LoginRegisterView {
    void whenRegisterSuccess();//注册成功
    void whenRegPhoneExist(String errorMsg);//手机号已经被注册
    void whenRegUmExist();//用户名已经被注册

    void whenLoginSuccess(String errorMsg);//登录成功
    void whenLoginPwErr(String errorMsg);//密码错误
    void whenLpginUmErr(String errorMsg);//用户名不存在
}
