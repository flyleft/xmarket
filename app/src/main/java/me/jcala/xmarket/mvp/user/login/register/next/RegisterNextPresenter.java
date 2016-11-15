package me.jcala.xmarket.mvp.user.login.register.next;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public interface RegisterNextPresenter {

    void getSchoolList();

    void registerNext(String user_id,String phone,String school);

    void checkPhone(TextInputLayout phoneLayout, EditText phone);
}
