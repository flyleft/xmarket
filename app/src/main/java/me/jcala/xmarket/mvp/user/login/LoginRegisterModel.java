package me.jcala.xmarket.mvp.user.login;


import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;

interface LoginRegisterModel {

    interface onLoginRegisterListener{
        void success(User user,String token);
        void complete(Result<String> result);
    }

    Result<String> loginRequest(final User userBean);
    void registerRequest(final User user,final onLoginRegisterListener listener);
}
