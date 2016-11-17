package me.jcala.xmarket.mvp.sort;


import java.util.List;

import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.util.CommonFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TradeTagModelImpl implements TradeTagModel {
    @Override
    public void getSortTag(final onGainListener listener) {
        Result<String>  result= CommonFactory.INSTANCE().server_error();
        ReqExecutor
                .INSTANCE()
                .tradeTagReq()
                .tradeTags()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<TradeTag>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure("");
                    }

                    @Override
                    public void onNext(Result<List<TradeTag>> listResult) {
                        if (listResult.getCode()== Api.SUCCESS.code()){
                            listener.onSuccess(listResult.getData());
                        }else {
                            listener.onFailure(listResult.getMsg());
                        }
                    }
                });
    }
}
