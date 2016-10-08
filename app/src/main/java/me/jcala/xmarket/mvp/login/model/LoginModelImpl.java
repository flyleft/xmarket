package me.jcala.xmarket.mvp.login.model;

import me.jcala.xmarket.data.entity.get.Result;
import me.jcala.xmarket.domain.UserBean;
import me.jcala.xmarket.service.UserSer;
import me.jcala.xmarket.service.impl.UserSerImpl;

/**
 * Created by Administrator on 2016/10/8.
 */

public class LoginModelImpl implements LoginModel {
    private UserSer userSer=new UserSerImpl();
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
