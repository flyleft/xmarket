package me.jcala.xmarket.mvp.user.login.register.next;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;

public class RegisterNextActivity extends BaseActivity implements RegisterNextView{

    private List<String> schools=new ArrayList<>();
    private String chooseSchool="";
    private RegisterNextPresenter presenter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.register_next_activity);
        initVariables();
    }

    protected void initVariables() {
        presenter=new RegisterNextPresenterImpl(this,this);
        presenter.getSchoolList();
    }

    @OnClick(R.id.register_next_school)
    public void showSingleChoice() {
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

    }

    @Override
    public void whenStopSetProgress() {

    }
}
