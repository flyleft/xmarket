package me.jcala.xmarket.mvp.trade.detail;

public interface TradeDetailPresenter {

    void loadData(String tradeId);

    void buyTrade(String tradeId);

}
