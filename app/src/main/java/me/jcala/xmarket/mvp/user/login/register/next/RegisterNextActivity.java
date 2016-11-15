package me.jcala.xmarket.mvp.user.login.register.next;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;
import me.jcala.xmarket.mvp.main.MainActivity;
import me.jcala.xmarket.util.CheckUtils;

public class RegisterNextActivity extends BaseActivity implements RegisterNextView{

    @BindView(R.id.register_next_phone_autocomplete)
    EditText phone;

    @BindView(R.id.register_next_phone)
    TextInputLayout phoneLayout;

    private List<String> schools=new ArrayList<>();

    private String chooseSchool="";

    private String userId="";

    @Inject
     RegisterNextPresenter presenter;

    @Inject
    MaterialDialog progress;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.register_next_activity);
        initVariables();
    }

    protected void initVariables() {
        Intent intent=new Intent();
        userId=intent.getStringExtra("userId");
        presenter.getSchoolList();
        presenter.checkPhone(phoneLayout,phone);
    }

    @OnClick(R.id.register_next_school)
    public void showSchoolChoice() {
        new MaterialDialog.Builder(this)
                .title(R.string.register_next_school_choose)
                .items(schools)
                .itemsCallbackSingleChoice(0,
                        (MaterialDialog dialog, View view, int which, CharSequence text)->{
                        chooseSchool=text.toString();
                        return true;
                })
                .positiveText(R.string.register_next_choose)
                .show();
    }

    @OnClick(R.id.register_next_sub)
    public void registerSub(){
        String phoneData=phone.getText().toString().trim();
        if (phoneData.isEmpty() || !CheckUtils.isNumber(phoneData)){
            presenter.registerNext(userId,phoneData,chooseSchool);
        }
    }

    @Override
    public void whenGetSchoolListSuccess(List<String> schoolList) {
        schools=schoolList;
    }

    @Override
    public void whenFails(String msg) {
        new SuperToast(RegisterNextActivity.this)
                .setText(msg)
                .setDuration(Style.DURATION_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_FLY)
                .show();
    }

    @Override
    public void whenStartSetProgress() {
      progress.show();
    }

    @Override
    public void whenStopSetProgress() {
      progress.dismiss();
    }

    @Override
    public void whenRegisterSuccess() {
        Intent intent=new Intent(RegisterNextActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
