package me.jcala.xmarket.mvp.presenter.impl;

import android.content.Context;

import me.jcala.xmarket.domain.UserBean;
import me.jcala.xmarket.mvp.model.LoginModel;
import me.jcala.xmarket.mvp.model.impl.LoginModelImpl;
import me.jcala.xmarket.mvp.presenter.LoginPre;
import me.jcala.xmarket.mvp.view.LoginView;

/**
 * Created by Administrator on 2016/10/8.
 */

public class LoginPreImpl implements LoginPre,LoginModelImpl.OnRegisterListener {
    private LoginView mView;
    private LoginModel mModel;
    private Context mContext;

    public LoginPreImpl(LoginView view,Context context){
        mView = view;
        mModel = new LoginModelImpl();
        mContext = context;
    }

    @Override
    public void doLogin(UserBean bean) {
         mModel.login(bean,this);
    }

    @Override
    public void onSuccess() {
        mView.showSuccessRegister();

    }

    @Override
    public void onFailure(String s) {
        mView.showFailureRegister(s);
    }
}
