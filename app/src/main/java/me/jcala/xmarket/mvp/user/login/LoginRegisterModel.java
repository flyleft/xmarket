package me.jcala.xmarket.mvp.user.login;

import org.jetbrains.annotations.NotNull;

import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.User;

interface LoginRegisterModel {
    interface onLoginRegisterListener{
        void success();
    }
    Result<String> loginRequest(@NotNull User userBean);
    Result<String> registerRequest(@NotNull User bean);
}
