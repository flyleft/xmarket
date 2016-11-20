package me.jcala.xmarket.mvp.user.trades.add;

import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.util.CommonFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TradeAddModelImpl implements TradeAddModel{

    @Override
    public void executeAddTradeReq(final String userId,
                                   final Trade trade,final onTradeAddListener listener) {
        @SuppressWarnings("unchecked")
        Result<String> result= CommonFactory.INSTANCE().server_error();
        ReqExecutor
                .INSTANCE()
                .userReq()
                .addTrade(userId,trade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<String>>() {
                    @Override
                    public void onCompleted() {
                       listener.hasGotAddTradeResult(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.hasGotAddTradeResult(result);
                    }

                    @Override
                    public void onNext(Result<String> resultData) {
                        result.setCode(resultData.getCode());
                        result.setMsg(resultData.getMsg());
                    }
                });
    }

}
