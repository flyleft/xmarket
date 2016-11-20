package me.jcala.xmarket.mvp.user.trades.add;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;

public interface TradeAddModel {

    interface onTradeAddListener{
        void hasGotAddTradeResult(Result<?> result);

    }
    void executeAddTradeReq(String userId,Trade trade,onTradeAddListener listener);
}
