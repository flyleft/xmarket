package me.jcala.xmarket.mvp.user.trade;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;

public interface UserTradeModel {
    interface OnUserTradeListener{
        void onCompleteListener(Result<List<Trade>> result);
    }
    void executeGetTradesReq(OnUserTradeListener listener,String userId,int type);
}
