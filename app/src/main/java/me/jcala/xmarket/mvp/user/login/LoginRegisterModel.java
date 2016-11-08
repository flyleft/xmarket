package me.jcala.xmarket.mvp.user.login;


import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;

interface LoginRegisterModel {
    interface onLoginRegisterListener{
        void success(User user,String token);
    }
    Result<String> loginRequest(User userBean);
    Result<String> registerRequest( User bean);
}
