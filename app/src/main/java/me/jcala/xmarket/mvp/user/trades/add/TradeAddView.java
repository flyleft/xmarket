package me.jcala.xmarket.mvp.user.trades.add;

public interface TradeAddView {
    void whenAddSuccess();
    void whenGetTagListSuccess();
    void whenFail(String errorMsg);
}
