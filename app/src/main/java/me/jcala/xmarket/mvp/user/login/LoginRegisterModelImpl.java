package me.jcala.xmarket.mvp.user.login;


import org.jetbrains.annotations.NotNull;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.conf.AppConf;
import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.UserBean;

 class LoginRegisterModelImpl implements LoginRegisterModel {
    @Override
    public Result<String> loginRequest(@NotNull UserBean userBean) {
        if (AppConf.reqExcute==AppConf.reqExcuteNormal){
           return executeLogin(userBean);
        }else {
           return executeLoginLocal(userBean);
        }

    }

    @Override
    public Result<String> registerRequest(@NotNull UserBean user) {
        if (AppConf.reqExcute==AppConf.reqExcuteNormal){
           return executeRegister(user);
        }else {
           return executeRegisterLocal(user);
        }

    }

    private Result<String> executeLogin(UserBean userBean){

         return null;
    }
     private Result<String> executeRegister(UserBean userBean){

       return null;
     }
    private Result<String> executeLoginLocal(UserBean userBean){
        Result<String> result=new Result<>();
        if ("jcala".equals(userBean.getUsername())&&"123".equals(userBean.getPassword())){
            result.setCode(ApiConf.action_success);
        }else if ("jcala".equals(userBean.getUsername())){
            result.setCode(ApiConf.login_pass_err);
        }else{
            result.setCode(ApiConf.login_um_err);
        }
        return result;
    }
    private Result<String> executeRegisterLocal(UserBean userBean){
        Result<String> result=new Result<>();
        if ("jcala".equals(userBean.getUsername())){
            result.setCode(ApiConf.register_um_exist);
        }else if ("187".equals(userBean.getPhone())){
            result.setCode(ApiConf.register_phone_exist);
        }else{
            result.setCode(ApiConf.action_success);
        }
        return result;
    }

}
