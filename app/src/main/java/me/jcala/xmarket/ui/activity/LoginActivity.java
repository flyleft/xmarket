package me.jcala.xmarket.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jcala.xmarket.R;
import me.jcala.xmarket.ui.base.BaseActivity;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login)
    MaterialLoginView login;
    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        handler();
    }
    private void handler(){
        ((DefaultLoginView)login.getLoginView()).setListener(new DefaultLoginView.DefaultLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                //Handle login
            }
        });

        ((DefaultRegisterView)login.getRegisterView()).setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
                //Handle register
            }
        });
    }
}
