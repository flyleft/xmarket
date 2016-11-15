package me.jcala.xmarket.mvp.user.login.register.next;

import java.util.List;

public interface RegisterNextView {
    void whenGetSchoolListSuccess(List<String> schoolList);//当获取学校列表成功后
    void whenFails(String msg);//当异常发生
    void whenStartSetProgress();//显示进度条
    void whenStopSetProgress();//隐藏进度条
    void whenRegisterSuccess();
}
