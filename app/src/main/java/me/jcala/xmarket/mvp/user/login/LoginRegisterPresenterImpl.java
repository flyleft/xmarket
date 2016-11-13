package me.jcala.xmarket.mvp.user.login;

import android.content.Context;

import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.util.TokenUtils;
import shem.com.materiallogin.MaterialLoginView;

public class LoginRegisterPresenterImpl implements
        LoginRegisterPresenter,
        LoginRegisterModel.onLoginRegisterListener{
    private LoginRegisterView view;
    private LoginRegisterModel model;
    private Context context;

    public LoginRegisterPresenterImpl(LoginRegisterView view,Context context) {
        this.context=context;
        this.view = view;
        this.model = new LoginRegisterModelImpl();
    }

    private LoginRegisterPresenterImpl() {
    }

    @Override
    public void login(String username,String password) {
        view.whenStartLoginProgress();
        model.loginRequest(username,password,this);
    }

    @Override
    public void register(String username,String password) {
        view.whenStartRegisterProgress();
        model.registerRequest(username,password,this);
    }

    @Override
    public void loginComplete(Result<User> result) {
        if (result==null){
            return;
        }

        view.whenStopLoginProgress();

        switch (result.getCode()) {
            case 100:
                TokenUtils.instance.saveUserToken(context,result.getData(),result.getData().getToken());
                ReqExecutor.INSTANCE().setToken(result.getData().getToken());
                view.whenLoginSuccess();
                break;
            case 203:
            case 204:
                view.whenLoginFail(result.getMsg());
                break;
            default:
                break;
        }
    }
    @Override
    public void registerComplete(Result<User> result) {
        //TokenUtils.instance.saveUserToken(context,user,token);//存储username,password以及token到SharedPreferences中
        //ReqExecutor.INSTANCE().setToken(token);//更新HTTP头部的token值
        if (result==null){
            return;
        }

        view.whenStopRegisterProgress();

        switch (result.getCode()) {
            case 100:
                TokenUtils.instance.saveUserToken(context,result.getData(),result.getData().getToken());
                ReqExecutor.INSTANCE().setToken(result.getData().getToken());
                view.whenRegisterSuccess();
                break;
            case 203:
            case 204:
                view.whenRegisterFail(result.getMsg());
                break;
            default:
                break;
        }
    }
}
