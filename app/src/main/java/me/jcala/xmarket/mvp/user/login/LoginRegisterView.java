package me.jcala.xmarket.mvp.user.login;


public interface LoginRegisterView {

    void whenLoginSuccess();//登录成功

    void whenStartLoginProgress();//显示登录进度条

    void whenStopLoginProgress();//隐藏登录进度条

    void whenStartRegisterProgress();//显示注册进度条

    void whenStopRegisterProgress();//隐藏注册进度条

    void whenRegisterSuccess(String userId);//注册成功

    void whenFail(String msg);//失败后要Toast的错误信息
}
