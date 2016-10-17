package me.jcala.xmarket.mvp.user.login;

import android.content.Intent;
import android.os.Bundle;
import com.sdsmdg.tastytoast.TastyToast;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import shem.com.materiallogin.MaterialLoginView;
public class LoginRegisterActivity extends BaseActivity implements LoginRegisterView {

    LoginRegisterPresenter presenter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        final MaterialLoginView loginRegister = (MaterialLoginView) findViewById(R.id.login_register);
        presenter=new LoginRegisterPresenterImpl(this);
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
        TastyToast.makeText(getApplicationContext(), errorMsg,
                TastyToast.LENGTH_SHORT, TastyToast.ERROR);
    }

}
