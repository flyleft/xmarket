package me.jcala.xmarket.mvp.user.login;


public interface LoginRegisterPresenter {
    void login(String username,String password);

    void register(String username,String password);
}
