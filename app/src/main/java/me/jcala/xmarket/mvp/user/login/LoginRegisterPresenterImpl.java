package me.jcala.xmarket.mvp.user.login;

import android.content.Context;

import me.jcala.xmarket.data.entity.UserBean;

public class LoginRegisterPresenterImpl implements
        LoginRegisterPresenter,
        LoginRegisterModel.onLoginListener,
        LoginRegisterModel.onRegisterListener{
    private LoginRegisterView view;
    private Context context;

    public LoginRegisterPresenterImpl(Context context, LoginRegisterView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void login(UserBean userBean) {

    }

    @Override
    public void register(UserBean userBean) {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void regPhoneExist(String errorMsg) {

    }

    @Override
    public void regUmExist() {

    }

    @Override
    public void loginSuccess(String errorMsg) {

    }

    @Override
    public void loginPwErr(String errorMsg) {

    }

    @Override
    public void lpginUmErr(String errorMsg) {

    }
}
