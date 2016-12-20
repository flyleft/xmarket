package me.jcala.xmarket.mvp.team.trade;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;

public interface TeamTradeModel {
    interface OnTeamTradeListener{
        void onComplete(Result<List<Trade>> result);
    }
    void executeGetTradeReq(OnTeamTradeListener listener,String team);
}
