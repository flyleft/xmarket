package me.jcala.xmarket.mvp.user.login.register.next;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.util.CheckUtils;

public class RegisterNextPresenterImpl
        implements RegisterNextPresenter,RegisterNextModel.onRegisterNextListener{

    private Context context;
    private RegisterNextModel model;
    private RegisterNextView view;

    public RegisterNextPresenterImpl(Context context, RegisterNextView view) {
        this.context = context;
        this.view = view;
        this.model=new RegisterNextModelImpl();
    }

    @Override
    public void getSchoolList() {
        model.executeSchoolRequest(this);
    }

    @Override
    public void hasGotSchoolList(Result<List<String>> result) {
        if (result==null){
            return;
        }
        switch (result.getCode()) {
            case 100:
                view.whenGetSchoolListSuccess(result.getData());
                break;
            case 99:
                view.whenFails(result.getMsg());
                break;
            default:
                break;
        }
    }

    @Override
    public void hasGotRegisterResult(Result<?> result) {

    }

    @Override
    public void registerNext(String phone, String school) {

    }


    @Override
    public void checkPhone(final TextInputLayout phoneLayout,final EditText phone) {
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //检查实际是否匹配，由自己实现
                String phoneData=phone.getText().toString().trim();
                if (phoneData.equals("") || phoneData.isEmpty()) {
                    phoneLayout.setErrorEnabled(true);
                    phoneLayout.setError("输入不可以为空");
                }else if (!CheckUtils.isNumber(phoneData)){
                    phoneLayout.setErrorEnabled(true);
                    phoneLayout.setError("只可以为数字");
                }else {
                    phoneLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
