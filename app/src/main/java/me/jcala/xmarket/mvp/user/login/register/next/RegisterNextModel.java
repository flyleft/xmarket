package me.jcala.xmarket.mvp.user.login.register.next;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;

public interface RegisterNextModel {

    interface onRegisterNextListener{

        void hasGotSchoolList(Result<List<String>> result);

        void hasGotRegisterResult(Result<User> result);
    }
   void executeSchoolRequest(onRegisterNextListener listener);

    void executeRegisterRequest(String user_id,String phone,String school,onRegisterNextListener listener);
}
