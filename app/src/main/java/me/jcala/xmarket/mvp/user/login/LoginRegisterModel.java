package me.jcala.xmarket.mvp.user.login;

import org.jetbrains.annotations.NotNull;

import me.jcala.xmarket.data.entity.UserBean;

public interface LoginRegisterModel {
    interface onLoginRegisterListener{
        void registerSuccess();
        void regPhoneExist(String errorMsg);
        void regUmExist();

        void loginSuccess(String errorMsg);
        void loginPwErr(String errorMsg);
        void lpginUmErr(String errorMsg);
    }
    void loginRequest(@NotNull UserBean userBean,onLoginRegisterListener listener);
}
