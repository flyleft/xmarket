package me.jcala.xmarket.mvp.user.presenter;

import android.content.Context;

import me.jcala.xmarket.data.entity.UserBean;
import me.jcala.xmarket.mvp.user.model.LoginModel;
import me.jcala.xmarket.mvp.user.model.LoginModelImpl;
import me.jcala.xmarket.mvp.user.view.LoginView;


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
