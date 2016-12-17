package me.jcala.xmarket.mvp.team.add;

public interface TeamAddView {
    void whenFail(String errorMsg);
    void whenSuccess();
    void whenStartProgress();//显示进度条
    void whenStopProgress();//隐藏进度条
}
