package me.jcala.xmarket.mvp.user.model;

import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.UserBean;

/**
 * Created by Administrator on 2016/10/8.
 */

public class LoginModelImpl implements LoginModel {
    @Override
    public void login(UserBean user, LoginModelImpl.OnRegisterListener listener) {
          Result result=login(user);
          if (result.getCode()==1){
              listener.onSuccess();
          }else{
              listener.onFailure(result.getMsg());
          }
    }
    public interface OnRegisterListener{
        void onSuccess();
        void onFailure(String s);
    }
    private Result login(UserBean userBean){
        return new Result();
    }
}
