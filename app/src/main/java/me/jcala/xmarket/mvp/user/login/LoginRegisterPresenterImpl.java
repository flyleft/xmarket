package me.jcala.xmarket.mvp.user.login;

import android.content.Context;
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
    public void login(MaterialLoginView loginView) {

    }

    @Override
    public void register(MaterialLoginView registerView) {
    }

    @Override
    public void complete(Result<String> result) {
        switch (result.getCode()) {
            case 100:
                break;
            case 203:
                break;
            case 204:
                break;
            default:
                break;
        }
    }
    @Override
    public void success(User user,String token) {
        TokenUtils.instance.saveUserToken(context,user,token);//存储username,password以及token到SharedPreferences中
        ReqExecutor.INSTANCE().setToken(token);//更新HTTP头部的token值
    }
}
