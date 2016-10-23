package me.jcala.xmarket.mvp.user.login;

import android.support.design.widget.TextInputLayout;
import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.dao.User;
import me.jcala.xmarket.util.CheckUtils;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

public class LoginRegisterPresenterImpl implements
        LoginRegisterPresenter,
        LoginRegisterModel.onLoginRegisterListener{
    private LoginRegisterView view;
    private LoginRegisterModel model;

    public LoginRegisterPresenterImpl(LoginRegisterView view) {
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
                            case ApiConf.action_success:
                                success();break;
                            case ApiConf.login_pass_err:
                                password.setErrorEnabled(true);
                                password.setError("密码错误");
                                break;
                            case ApiConf.login_um_err:
                                username.setErrorEnabled(true);
                                username.setError("该用户不存在");
                                break;
                            default:username.setErrorEnabled(true);
                                username.setError("网络错误,请稍后再试");
                                break;
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
                        Result<String> result= model.registerRequest(bean);
                        switch (result.getCode()){
                            case ApiConf.action_success:
                                success();break;
                            case ApiConf.register_um_exist:
                                username.setErrorEnabled(true);
                                username.setError("该用户名已存在");
                                break;
                            case ApiConf.register_phone_exist:
                                phone.setErrorEnabled(true);
                                phone.setError("该手机号已被注册");
                                break;
                            default:username.setErrorEnabled(true);
                                username.setError("网络错误,请稍后再试");
                                break;
                        }

                    }
            });
    }

    @Override
    public void success() {
        view.whenSuccess();
    }
}
