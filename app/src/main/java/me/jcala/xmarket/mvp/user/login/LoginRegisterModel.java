package me.jcala.xmarket.mvp.user.login;

import org.jetbrains.annotations.NotNull;

import me.jcala.xmarket.data.entity.UserBean;

public interface LoginRegisterModel {
    interface onLoginListener{
        void loginSuccess(String errorMsg);
        void loginPwErr(String errorMsg);
        void lpginUmErr(String errorMsg);
    }
    interface onRegisterListener{
        void registerSuccess();
        void regPhoneExist(String errorMsg);
        void regUmExist();
    }
    void loginRequest(@NotNull UserBean userBean,onLoginListener listener);
    void registerRequest(@NotNull UserBean bean,onRegisterListener listener);
}
