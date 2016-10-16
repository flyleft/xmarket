package me.jcala.xmarket.mvp.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;

import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;
public class LoginRegisterActivity extends BaseActivity implements LoginRegisterView {
    MaterialLoginView login;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        login=(MaterialLoginView)findViewById(R.id.login);
        handler();
    }
    private void handler(){
        ((DefaultLoginView)login.getLoginView()).setListener(new DefaultLoginView.DefaultLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                Intent intent=new Intent(LoginRegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ((DefaultRegisterView)login.getRegisterView()).setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
                //Handle register
            }
        });
    }

    @Override
    public void whenRegisterSuccess() {

    }

    @Override
    public void whenRegPhoneExist(String errorMsg) {

    }

    @Override
    public void whenRegUmExist() {

    }

    @Override
    public void whenLoginSuccess(String errorMsg) {

    }

    @Override
    public void whenLoginPwErr(String errorMsg) {

    }

    @Override
    public void whenLpginUmErr(String errorMsg) {

    }
}
