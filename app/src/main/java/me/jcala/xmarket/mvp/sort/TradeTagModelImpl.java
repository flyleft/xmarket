package me.jcala.xmarket.mvp.sort;


import java.util.List;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.api.TradeReq;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.mock.TradeMock;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class TradeTagModelImpl implements TradeTagModel {
    @Override
    public void getSortTag(final onGainListener listener) {
        if (AppConf.useMock){
            listener.onComplete(new TradeMock().tradeTag());
            return;
        }
        Result<List<TradeTag>> result = new Result<List<TradeTag>>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .tradeReq()
                .tradeTags(TradeReq.GET_TAG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<TradeTag>>>() {
                    @Override
                    public void onCompleted() {
                        listener.onComplete(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(result.getMsg());
                    }
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onNext(Result<List<TradeTag>> listResult) {
                        result.setCode(listResult.getCode());
                        result.setMsg(listResult.getMsg());
                        result.setData(listResult.getData());
                    }
                });
    }
}
