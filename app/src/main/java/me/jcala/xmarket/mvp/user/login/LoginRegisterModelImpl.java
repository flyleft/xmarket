package me.jcala.xmarket.mvp.user.login;



import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.conf.AppConf;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.dao.User;

 class LoginRegisterModelImpl implements LoginRegisterModel {
    @Override
    public Result<String> loginRequest(User userBean) {
        if (AppConf.reqExcute==AppConf.reqExcuteNormal){
           return executeLogin(userBean);
        }else {
           return executeLoginLocal(userBean);
        }

    }

    @Override
    public Result<String> registerRequest(User user) {
        if (AppConf.reqExcute==AppConf.reqExcuteNormal){
           return executeRegister(user);
        }else {
           return executeRegisterLocal(user);
        }

    }

    private Result<String> executeLogin(User userBean){

         return null;
    }
     private Result<String> executeRegister(User userBean){

       return null;
     }
    private Result<String> executeLoginLocal(User userBean){
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
    private Result<String> executeRegisterLocal(User userBean){
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
