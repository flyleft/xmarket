package me.jcala.xmarket.mvp.sort.trades;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;

public interface TradeTagDetailModel {

    interface onGainListener{
        void onComplete(Result<List<Trade>> result);
    }
    void executeTagTradesReq(onGainListener listener,String tagName, int page);
}
