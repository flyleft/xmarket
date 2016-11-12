package me.jcala.xmarket.mvp.user.login;

import android.content.Context;
import android.support.design.widget.TextInputLayout;

import com.orhanobut.logger.Logger;

import java.util.concurrent.CopyOnWriteArrayList;

import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.util.CheckUtils;
import me.jcala.xmarket.util.TokenUtils;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
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
        ((DefaultLoginView)loginView.getLoginView()).setListener(
                (TextInputLayout username, TextInputLayout password)->{
                    User bean=new User();
                    bean.setUsername(username.getEditText().getText().toString());
                    bean.setPassword(password.getEditText().getText().toString());
                    if (CheckUtils.isEmpty(bean.getUsername())){
                        username.setErrorEnabled(true);
                        username.setError("用户名不可以为空");
                    }else if (CheckUtils.isEmpty(bean.getPassword())){
                        password.setErrorEnabled(true);
                        password.setError("密码不可以为空");
                    }else{
                        username.setErrorEnabled(false);
                        password.setErrorEnabled(false);
                        Result<String> result= model.loginRequest(bean);
                        switch (result.getCode()){
                            case 100:
                                success(bean,result.getData());break;
                            case 202:
                                password.setErrorEnabled(true);
                                password.setError(Api.USER_PASS_ERR.msg());
                                break;
                            case 201:
                                username.setErrorEnabled(true);
                                username.setError(Api.USER_NOT_EXIST.msg());
                                break;
                            default:break;
                        }

                    }
            });

    }

    @Override
    public void register(MaterialLoginView registerView) {
        ((DefaultRegisterView)registerView.getRegisterView()).setListener(
                (TextInputLayout username, TextInputLayout phone,
                 TextInputLayout password)-> {
                    User bean=new User();
                    bean.setUsername(username.getEditText().getText().toString());
                    bean.setPhone(phone.getEditText().getText().toString());
                    bean.setPassword(password.getEditText().getText().toString());

                    if (CheckUtils.isEmpty(bean.getUsername())){
                        username.setErrorEnabled(true);
                        username.setError("用户名不可以为空");
                    }else if (CheckUtils.isEmpty(bean.getPhone())){
                        username.setErrorEnabled(false);
                        phone.setErrorEnabled(true);
                        phone.setError("手机号不可以为空");

                    }else if (!CheckUtils.isNumber(bean.getPhone())){
                        phone.setErrorEnabled(true);
                        phone.setError("不合法的手机号");
                    }else if (CheckUtils.isEmpty(bean.getPassword())){
                        password.setErrorEnabled(true);
                        password.setError("密码不可以为空");
                    }else{
                        username.setErrorEnabled(false);
                        phone.setErrorEnabled(false);
                        password.setErrorEnabled(false);
                        model.registerRequest(bean,this);
                        /*Logger.i("register",result.toString());
                        switch (result.getCode()){
                            case 100:

                                success(bean,result.getData());break;
                            case 203:
                                username.setErrorEnabled(true);
                                username.setError(Api.USER_NAME_EXIST.msg());
                                break;
                            case 204:
                                phone.setErrorEnabled(true);
                                phone.setError(Api.USER_PHONE_EXIST.msg());
                                break;
                            default:break;
                        }*/

                    }
            });
    }

    @Override
    public void complete(Result<String> result) {

    }

    @Override
    public void success(User user,String token) {
        TokenUtils.instance.saveUserToken(context,user,token);//存储username,password以及token到SharedPreferences中
        ReqExecutor.INSTANCE().setToken(token);//更新HTTP头部的token值
        view.whenSuccess();
    }
}
