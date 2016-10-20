package me.jcala.xmarket.mvp.user.login;

import android.content.Intent;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;

import javax.inject.Inject;

import me.jcala.xmarket.R;
import me.jcala.xmarket.di.components.DaggerLoginRegisterComponent;
import me.jcala.xmarket.di.modules.LoginRegisterModule;
import me.jcala.xmarket.di.qualifier.LoginProgress;
import me.jcala.xmarket.di.qualifier.RegisterProgress;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import shem.com.materiallogin.MaterialLoginView;

public class LoginRegisterActivity extends BaseActivity implements LoginRegisterView {

    @Inject
    LoginRegisterPresenter presenter;

    @LoginProgress
    @Inject
    MaterialDialog loginProgress;

    @RegisterProgress
    @Inject
    MaterialDialog registerProgress;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        initVariables();
    }
    @Override
    protected  void initVariables(){
        DaggerLoginRegisterComponent
                .builder()
                .loginRegisterModule(new LoginRegisterModule(this,this))
                .build()
                .inject(this);
        final MaterialLoginView loginRegister = (MaterialLoginView) findViewById(R.id.login_register);
        if (loginRegister!=null){
            presenter.login(loginRegister);
            presenter.register(loginRegister);
        }
    }
    @Override
    public void whenSuccess() {
        Intent intent=new Intent(LoginRegisterActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
