package me.jcala.xmarket.mvp.model;

import me.jcala.xmarket.domain.UserBean;
import me.jcala.xmarket.mvp.model.impl.LoginModelImpl;

/**
 * Created by Administrator on 2016/10/8.
 */

public interface LoginModel {
    void login(UserBean user,LoginModelImpl.OnRegisterListener listener);
}
