package me.jcala.xmarket.mvp.user.login;

public interface LoginRegisterView {
    void whenRegSuccess();
    void whenRegUmExist();
    void whenRegPhoneExist();
    void whenLoginPwErr();
    void whenLoginUmErr();
    void showProgress();
    void hideProgress();
}
