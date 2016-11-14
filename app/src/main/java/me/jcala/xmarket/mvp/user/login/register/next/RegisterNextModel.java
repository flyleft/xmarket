package me.jcala.xmarket.mvp.user.login.register.next;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;

public interface RegisterNextModel {

    interface onRegisterNextListener{

        void hasGotSchoolList(Result<List<String>> result);

        void hasGotRegisterResult(Result<?> result);
    }

}
