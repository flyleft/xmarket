package me.jcala.xmarket.mvp.login.model;

import javax.inject.Inject;

import me.jcala.xmarket.data.entity.get.Result;
import me.jcala.xmarket.domain.UserBean;
import me.jcala.xmarket.service.UserSer;

/**
 * Created by Administrator on 2016/10/8.
 */

public class LoginModelImpl implements LoginModel {
    @Inject
    private UserSer userSer;
    @Override
    public void login(UserBean user, LoginModelImpl.OnRegisterListener listener) {
          Result result=userSer.login(user);
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
}
