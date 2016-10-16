package me.jcala.xmarket.mvp.user.login;


import shem.com.materiallogin.MaterialLoginView;

public interface LoginRegisterPresenter {
    void login(MaterialLoginView view);
    void register(MaterialLoginView view);
}
