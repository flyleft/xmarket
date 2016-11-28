package me.jcala.xmarket.mvp.user.trades.add;

import me.jcala.xmarket.data.pojo.Trade;

public interface TradeAddPresenter {
    void tradeAdd(Trade trade);
    void gainTagList();
}
