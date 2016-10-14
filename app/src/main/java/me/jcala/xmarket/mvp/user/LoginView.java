package me.jcala.xmarket.mvp.user;

public interface LoginView {
    void usernameErrorView();

    void passwordErrorView();

    void onSuccessView();
}
