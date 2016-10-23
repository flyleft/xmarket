package me.jcala.xmarket.mvp.user.login;


import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.User;

interface LoginRegisterModel {
    interface onLoginRegisterListener{
        void success();
    }
    Result<String> loginRequest(User userBean);
    Result<String> registerRequest( User bean);
}
