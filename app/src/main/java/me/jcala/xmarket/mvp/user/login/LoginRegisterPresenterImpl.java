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

    @Override
    public void login(@NotNull MaterialLoginView view) {
        ((DefaultLoginView)view.getLoginView()).setListener(
                (TextInputLayout username, TextInputLayout password)->{
                    UserBean bean=new UserBean();
                    bean.setUsername(username.getEditText().getText().toString());
                    bean.setPassword(password.getEditText().getText().toString());
                    Logger.d(bean.toString());
                    if (!CheckUtils.checkLoginBean(bean)){
                        hasNull();
                    }else{
                        model.loginRequest(bean,this);
                    }
            });

    }

    @Override
    public void register(@NotNull MaterialLoginView view) {
        ((DefaultRegisterView)view.getRegisterView()).setListener(
                (TextInputLayout username, TextInputLayout phone,
                 TextInputLayout password)-> {
                    UserBean bean=new UserBean();
                    bean.setUsername(username.getEditText().getText().toString());
                    bean.setPhone(password.getEditText().getText().toString());
                    bean.setPassword(password.getEditText().getText().toString());
                    if (!CheckUtils.checkRegisterBean(bean)){
                        hasNull();
                    }else{
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
