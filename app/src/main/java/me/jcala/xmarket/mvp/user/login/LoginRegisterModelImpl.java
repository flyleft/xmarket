package me.jcala.xmarket.mvp.user.login;



import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;

 class LoginRegisterModelImpl implements LoginRegisterModel {
    @Override
    public Result<String> loginRequest(User userBean) {
        if (ApiConf.excute==1){
           return executeLogin(userBean);
        }else {
           return executeLoginLocal(userBean);
        }

    }

    @Override
    public Result<String> registerRequest(User user) {
        if (ApiConf.excute==1){
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
            result.setCode(1);
        }else if ("jcala".equals(userBean.getUsername())){
            result.setCode(0);
        }else{
            result.setCode(2);
        }
        return result;
    }
    private Result<String> executeRegisterLocal(User userBean){
        Result<String> result=new Result<>();
        if ("jcala".equals(userBean.getUsername())){
            result.setCode(2);
        }else if ("187".equals(userBean.getPhone())){
            result.setCode(3);
        }else{
            result.setCode(1);
        }
        return result;
    }

}
