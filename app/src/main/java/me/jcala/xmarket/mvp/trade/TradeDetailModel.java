package me.jcala.xmarket.mvp.trade;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;

public interface TradeDetailModel {

    interface onDetailListener{

        void onComplete(Result<Trade> result);

    }

    void executeDetailReq(onDetailListener listener,String tradeId);

}
