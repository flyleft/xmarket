package me.jcala.xmarket.mvp.user;

public interface LoginView {
    void whenUsernameError();

    void whenPasswordError();

    void whenSuccess();
}
