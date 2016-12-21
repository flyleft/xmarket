package me.jcala.xmarket.mvp.team.trade;

import java.util.List;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.network.Api;
import me.jcala.xmarket.network.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TeamTradeModelImpl implements TeamTradeModel{

    @Override
    public void executeGetTradeReq(OnTeamTradeListener listener, String team) {
        Result<List<Trade>> result = new Result<List<Trade>>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .tradeReq()
                .getTeamTrades(team,0, AppConf.size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<Trade>>>() {
                    @Override
                    public void onCompleted() {
                        listener.onComplete(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onComplete(result);
                    }
                    @Override
                    public void onNext(Result<List<Trade>> listResult) {
                        result.setCode(listResult.getCode());
                        result.setMsg(listResult.getMsg());
                        result.setData(listResult.getData());
                    }
                });
    }

}
