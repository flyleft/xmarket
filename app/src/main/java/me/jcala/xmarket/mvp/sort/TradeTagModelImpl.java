package me.jcala.xmarket.mvp.sort;


import java.util.List;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.mock.TradeTagMock;
import me.jcala.xmarket.util.CommonFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class TradeTagModelImpl implements TradeTagModel {
    @Override
    public void getSortTag(final onGainListener listener) {
        if (AppConf.useMock){
            listener.onComplete(new TradeTagMock().tradeTag());
            return;
        }
        @SuppressWarnings("unchecked")
        Result result = CommonFactory.INSTANCE().server_error();
        ReqExecutor
                .INSTANCE()
                .tradeTagReq()
                .tradeTags()
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
