package me.jcala.xmarket.mvp.user.login;

import android.content.Intent;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sdsmdg.tastytoast.TastyToast;

import javax.inject.Inject;

import me.jcala.xmarket.R;
import me.jcala.xmarket.di.components.DaggerLoginRegisterComponent;
import me.jcala.xmarket.di.components.DaggerSortTagComponent;
import me.jcala.xmarket.di.modules.LoginRegisterModule;
import me.jcala.xmarket.di.modules.SortTagModule;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import shem.com.materiallogin.MaterialLoginView;
public class LoginRegisterActivity extends BaseActivity implements LoginRegisterView {

    @Inject
    LoginRegisterPresenter presenter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        final MaterialLoginView loginRegister = (MaterialLoginView) findViewById(R.id.login_register);
        DaggerLoginRegisterComponent
                .builder()
                .loginRegisterModule(new LoginRegisterModule(this))
                .build()
                .inject(this);
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

    @Override
    public void whenErr(String errorMsg) {
        /*TastyToast.makeText(getApplicationContext(), errorMsg,
                TastyToast.LENGTH_SHORT, TastyToast.ERROR);*/
        /*Intent intent=new Intent(LoginRegisterActivity.this,MainActivity.class);
        startActivity(intent);
        finish();*/
        new MaterialDialog.Builder(this)
                .title(R.string.no_content)
                .content(R.string.login_dialog_content)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .show();
    }

    @Override
    public void showProgress(int which) {
        new MaterialDialog.Builder(this)
                .title(R.string.no_content)
                .content(R.string.login_dialog_content)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .show();
    }

    @Override
    public void hideProgress() {

    }
}
