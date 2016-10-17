package me.jcala.xmarket.mvp.user.login;

import org.jetbrains.annotations.NotNull;

import me.jcala.xmarket.data.entity.UserBean;

interface LoginRegisterModel {
    interface onLoginRegisterListener{
        void loginSuccess();
        void loginPwErr();
        void loginUmErr();
        void registerSuccess();
        void regPhoneExist();
        void regUmExist();
        void hasNull();
    }
    void loginRequest(@NotNull UserBean userBean,onLoginRegisterListener listener);
    void registerRequest(@NotNull UserBean bean,onLoginRegisterListener listener);
}
