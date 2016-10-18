package me.jcala.xmarket.mvp.user.login;


public interface LoginRegisterView {
    void whenSuccess();//登录或者注册成功
    void whenErr(String errorMsg);//发生错误
    void showProgress(boolean login);//开始进度条
    void hideProgress();//结束进度条
}
