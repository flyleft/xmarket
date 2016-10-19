package me.jcala.xmarket.mvp.user.login;

import android.support.design.widget.TextInputLayout;

import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.NotNull;
import me.jcala.xmarket.data.entity.UserBean;
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
    public void login(@NotNull MaterialLoginView loginView) {
        ((DefaultLoginView)loginView.getLoginView()).setListener(
                (TextInputLayout username, TextInputLayout password)->{
                    UserBean bean=new UserBean();
                    bean.setUsername(username.getEditText().getText().toString());
                    bean.setPassword(password.getEditText().getText().toString());
                    if (CheckUtils.isEmpty(bean.getUsername())){
                        username.setErrorEnabled(true);
                        username.setError("用户名不可以为空");
                    }else if (CheckUtils.isEmpty(bean.getPassword())){
                        username.setErrorEnabled(false);
                        password.setErrorEnabled(true);
                        password.setError("密码不可以为空");
                    }else{
                        username.setErrorEnabled(false);
                        password.setErrorEnabled(false);
                        model.loginRequest(bean,this);
                    }
            });

    }

    @Override
    public void register(@NotNull MaterialLoginView registerView) {
        ((DefaultRegisterView)registerView.getRegisterView()).setListener(
                (TextInputLayout username, TextInputLayout phone,
                 TextInputLayout password)-> {
                    UserBean bean=new UserBean();
                    bean.setUsername(username.getEditText().getText().toString());
                    bean.setPhone(phone.getEditText().getText().toString());
                    bean.setPassword(password.getEditText().getText().toString());

                    if (CheckUtils.isEmpty(bean.getUsername())){
                        username.setErrorEnabled(true);
                        username.setError("用户名不可以为空");
                        Logger.e("username is empty");
                    }else if (CheckUtils.isEmpty(bean.getPhone())){
                        username.setErrorEnabled(false);
                        phone.setErrorEnabled(true);
                        phone.setError("手机号不可以为空");
                        Logger.e("phone is empty");

                    }else if (!CheckUtils.isNumber(bean.getPhone())){
                        phone.setErrorEnabled(true);
                        phone.setError("不合法的手机号");
                        Logger.e("username is illegal");
                    }else if (CheckUtils.isEmpty(bean.getPassword())){
                        phone.setErrorEnabled(false);
                        username.setErrorEnabled(false);
                        password.setErrorEnabled(true);
                        password.setError("密码不可以为空");
                        Logger.e("password is empty");
                    }else{
                        username.setErrorEnabled(false);
                        phone.setErrorEnabled(false);
                        password.setErrorEnabled(false);
                        model.registerRequest(bean,this);
                    }
            });
    }

    @Override
    public void hasNull() {
        view.whenErr("输入的值不可以为空");
    }

    @Override
    public void registerSuccess() {
        view.whenSuccess();
    }

    @Override
    public void regPhoneExist() {
        view.whenErr("该手机号已经被注册");
    }

    @Override
    public void regUmExist() {
        view.whenErr("该用户名已存在");
    }

    @Override
    public void loginSuccess() {
       view.whenSuccess();
    }

    @Override
    public void loginPwErr() {
        view.whenErr("密码错误");
    }

    @Override
    public void loginUmErr() {
        view.whenErr("该用户名不存在");
    }
}
