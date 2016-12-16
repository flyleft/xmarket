package me.jcala.xmarket.mvp.trade.detail;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.mock.TradeMock;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TradeDetailModelImpl implements TradeDetailModel {

    @Override
    public void executeDetailReq(onDetailListener listener,String tradeId) {

        if (AppConf.useMock){
            listener.onGainDealComplete(new TradeMock().gainTradeDetail());
            return;
        }

        Result<Trade> result = new Result<Trade>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .tradeReq()
                .tradeDetail(tradeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<Trade>>() {
                    @Override
                    public void onCompleted() {
                        listener.onGainDealComplete(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onGainDealComplete(result);
                    }
                    @Override
                    public void onNext(Result<Trade> tradeResult) {
                        result.setCode(tradeResult.getCode());
                        result.setMsg(tradeResult.getMsg());
                        result.setData(tradeResult.getData());
                    }
                });

    }

    @Override
    public void executeBuyReq(final onDetailListener listener,final User user,final String tradeId) {

        if (AppConf.useMock){
            return;
        }

        Result<String> result =new Result<String>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .hybridReq()
                .createDeal(user.getId(),user.getUsername(),user.getAvatarUrl(),tradeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<String>>() {
                    @Override
                    public void onCompleted() {
                        listener.onBuyComplete(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onBuyComplete(result);
                    }
                    @Override
                    public void onNext(Result<String> tradeResult) {
                        result.setCode(tradeResult.getCode());
                        result.setMsg(tradeResult.getMsg());
                    }
                });
    }
}
