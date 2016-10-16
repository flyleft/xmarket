package me.jcala.xmarket.mvp.user.login;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
public class LoginRegisterActivity extends BaseActivity implements LoginRegisterView {

    private Unbinder unbinder;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        unbinder=ButterKnife.bind(this);
        //login=(MaterialLoginView)findViewById(R.id.login);
        handler();
    }
    private void handler(){
    }
    @Override
    public void whenRegSuccess() {

    }

    @Override
    public void whenRegUmExist() {

    }

    @Override
    public void whenRegPhoneExist() {

    }

    @Override
    public void whenLoginPwErr() {

    }

    @Override
    public void whenLoginUmErr() {

    }
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
