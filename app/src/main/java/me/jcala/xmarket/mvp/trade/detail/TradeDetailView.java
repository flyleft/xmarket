package me.jcala.xmarket.mvp.trade.detail;

import java.util.List;

import me.jcala.xmarket.data.pojo.Trade;

public interface TradeDetailView {

    void whenLoadTradeSuccess(Trade trade);

    void whenLoadTeamSuccess(List<String> teams);

    void whenBuySuccess();

    void whenDonateSuccess();

    void whenFail(String errorMsg);

    void whenShowProgress();

    void whenHideProgress();

}
