package me.jcala.xmarket.mvp.trade.detail;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.User;

public interface TradeDetailModel {

    interface onDetailListener{

        void onGainDealComplete(Result<Trade> result);

        void onBuyComplete(Result<String> result);

        void onGainTeamNamesComplete(Result<List<String>> result);
    }

    void executeDetailReq(onDetailListener listener,String tradeId);

    void executeBuyReq(onDetailListener listener, User user, String tradeId);

    void executeGetTeamNamesReq(onDetailListener listener,String schoolName);
}
