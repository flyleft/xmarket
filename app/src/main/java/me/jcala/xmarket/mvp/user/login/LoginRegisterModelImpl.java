package me.jcala.xmarket.mvp.user.login;


import org.jetbrains.annotations.NotNull;

import me.jcala.xmarket.conf.AppConf;
import me.jcala.xmarket.data.entity.UserBean;

public class LoginRegisterModelImpl implements LoginRegisterModel {

    @Override
    public void loginRequest(@NotNull UserBean userBean, onLoginRegisterListener listener) {
        if (AppConf.reqExcute==AppConf.reqExcuteNormal){
            executeLogin(userBean,listener);
        }else {
            executeLoginLocal(userBean,listener);
        }

    }

    @Override
    public void registerRequest(@NotNull UserBean userBean, onLoginRegisterListener listener) {
        if (AppConf.reqExcute==AppConf.reqExcuteNormal){
            executeRegister(userBean,listener);
        }else {
            executeRegisterLocal(userBean,listener);
        }


    }

    private void executeLogin(UserBean userBean, onLoginRegisterListener listener){


    }
    private void executeLoginLocal(UserBean userBean, onLoginRegisterListener listener){
        if ("jcala".equals(userBean.getUsername())&&"123".equals(userBean.getPassword())){
            listener.loginSuccess();
        }else if ("jcala".equals(userBean.getUsername())){
            listener.loginPwErr();
        }else{
            listener.loginUmErr();
        }

    }
    private void executeRegister(UserBean userBean,  onLoginRegisterListener listener){

    }
    private void executeRegisterLocal(UserBean userBean,  onLoginRegisterListener listener){
        if ("jcala".equals(userBean.getUsername())){
            listener.regUmExist();
        }else if ("187".equals(userBean.getPhone())){
            listener.regPhoneExist();
        }else{
            listener.registerSuccess();
        }
    }

}
