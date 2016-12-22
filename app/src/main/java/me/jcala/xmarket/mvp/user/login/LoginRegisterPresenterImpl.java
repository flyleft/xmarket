package me.jcala.xmarket.mvp.user.login;

import android.content.Context;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.data.storage.SharedPreferencesStorage;
import me.jcala.xmarket.util.Encrypt;

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
        password = new Encrypt().SHA256(password);
        model.loginRequest(username,password,this);
    }

    @Override
    public void register(String username,String password) {
        view.whenStartRegisterProgress();
        password = new Encrypt().SHA256(password);
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
                SharedPreferencesStorage.instance.saveUser(context,result.getData());
                view.whenLoginSuccess();
                break;
            case 99:
            case 201:
            case 202:
                view.whenFail(result.getMsg());
                break;
            default:
                break;
        }
    }
    @Override
    public void registerComplete(Result<String> result) {
        if (result==null){
            return;
        }
        
        view.whenStopRegisterProgress();

        switch (result.getCode()) {
            case 100:
                view.whenRegisterSuccess(result.getData());
                break;
            case 99:
            case 203:
            case 204:
                view.whenFail(result.getMsg());
                break;
            default:
                break;
        }
    }
}
