package me.jcala.xmarket.mvp.trade.detail;

import me.jcala.xmarket.data.pojo.Trade;

public interface TradeDetailView {

    void whenSuccess(Trade trade);

    void whenFail(String errorMsg);

    void whenBuySuccess();

}
