package me.jcala.xmarket.mvp.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import javax.inject.Inject;
import me.jcala.xmarket.R;
import me.jcala.xmarket.di.components.DaggerLoginRegisterComponent;
import me.jcala.xmarket.di.modules.LoginRegisterModule;
import me.jcala.xmarket.di.qualifier.LoginProgress;
import me.jcala.xmarket.di.qualifier.RegisterProgress;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.mvp.user.login.register.next.RegisterNextActivity;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
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

    protected  void initVariables(){
        DaggerLoginRegisterComponent
                .builder()
                .loginRegisterModule(new LoginRegisterModule(this,this))
                .build()
                .inject(this);
        final MaterialLoginView loginRegister = (MaterialLoginView) findViewById(R.id.login_register);
        ((DefaultLoginView)loginRegister.getLoginView()).setListener(
                (TextInputLayout username, TextInputLayout password) -> {
                    String name=username.getEditText().getText().toString();
                    String pass=password.getEditText().getText().toString();
                  if (name.isEmpty()){
                      username.setErrorEnabled(true);
                      username.setError("用户名不可以为空");
                  }else if (pass.isEmpty()){
                      password.setErrorEnabled(true);
                      password.setError("密码不可以为空");
                  }else{
                          presenter.login(name,pass);
                  }
                }
        );

        ((DefaultRegisterView)loginRegister.getRegisterView()).setListener(
                (TextInputLayout username, TextInputLayout password, TextInputLayout repeatPassword) ->{
                    String name=username.getEditText().getText().toString();
                    String pass=password.getEditText().getText().toString();
                    String repeatPass=repeatPassword.getEditText().getText().toString();
                    if (name.isEmpty()){
                        username.setErrorEnabled(true);
                        username.setError("用户名不可以为空");
                    }else if (pass.isEmpty()){
                        password.setErrorEnabled(true);
                        password.setError("密码不可以为空");
                    }else if (repeatPass.isEmpty()){
                        repeatPassword.setErrorEnabled(true);
                        repeatPassword.setError("密码不可以为空");
                    }else if (!pass.equals(repeatPass)){
                        repeatPassword.setErrorEnabled(true);
                        repeatPassword.setError("两次密码输入不一致");
                    }else{
                        presenter.register(name,pass);
                    }
                }
        );
    }

    @Override
    public void whenLoginSuccess() {
        Intent intent=new Intent(LoginRegisterActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void whenRegisterSuccess(final String userId) {
         Intent intent=new Intent();
         intent.setClass(LoginRegisterActivity.this,RegisterNextActivity.class);
         intent.putExtra("userId",userId);
         startActivity(intent);
         finish();
    }

    @Override
    public void whenFail(String msg) {
        new SuperToast(LoginRegisterActivity.this)
                .setText(msg)
                .setDuration(Style.DURATION_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_POP)
                .show();
    }

    @Override
    public void whenStartLoginProgress() {loginProgress.show();}

    @Override
    public void whenStopLoginProgress() {loginProgress.dismiss();}

    @Override
    public void whenStartRegisterProgress() {registerProgress.show();}

    @Override
    public void whenStopRegisterProgress() {registerProgress.dismiss();}
}
