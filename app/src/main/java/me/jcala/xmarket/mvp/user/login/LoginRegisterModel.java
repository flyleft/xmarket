package me.jcala.xmarket.mvp.user.login;

import org.jetbrains.annotations.NotNull;

import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.UserBean;

interface LoginRegisterModel {
    interface onLoginRegisterListener{
        void success();
    }
    Result<String> loginRequest(@NotNull UserBean userBean);
    Result<String> registerRequest(@NotNull UserBean bean);
}
