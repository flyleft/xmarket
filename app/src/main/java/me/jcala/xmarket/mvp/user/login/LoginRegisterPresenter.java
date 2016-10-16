package me.jcala.xmarket.mvp.user.login;


import me.jcala.xmarket.data.entity.UserBean;

public interface LoginRegisterPresenter {
    void login(UserBean userBean);
    void register(UserBean userBean);
}
