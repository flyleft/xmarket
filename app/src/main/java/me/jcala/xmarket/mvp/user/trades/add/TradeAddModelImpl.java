package me.jcala.xmarket.mvp.user.trades.add;

import java.util.List;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.api.TradeReq;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.mock.TradeMock;
import me.jcala.xmarket.util.CommonFactory;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TradeAddModelImpl implements TradeAddModel{

    @Override
    public void executeAddTradeReq(final RequestBody tradeJson, final String userId,
                                   final List<MultipartBody.Part> pics, final onTradeAddListener listener) {
        @SuppressWarnings("unchecked")
        Result<String> result= CommonFactory.INSTANCE().server_error();
        ReqExecutor
                .INSTANCE()
                .tradeReq()
                .addTrade(userId,tradeJson,pics)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<String>>() {
                    @Override
                    public void onCompleted() {
                       listener.hasGotAddTradeResult(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        result.setCode(Api.SERVER_ERROR.code());
                        listener.hasGotAddTradeResult(result);
                    }

                    @Override
                    public void onNext(Result<String> resultData) {
                        result.setCode(resultData.getCode());
                        result.setMsg(resultData.getMsg());
                    }
                });
    }

    @Override
    public void executeGetTagsReq(onTradeAddListener listener) {
        if (AppConf.useMock){
            listener.hasGoTagsResult(new TradeMock().tradeTagStrings());
            return;
        }
        @SuppressWarnings("unchecked")
        Result<List<String>> result= CommonFactory.INSTANCE().server_error();

        ReqExecutor
                .INSTANCE()
                .tradeReq()
                .tagNames(TradeReq.GET_NAME)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<String>>>() {
                    @Override
                    public void onCompleted() {
                        listener.hasGoTagsResult(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.hasGoTagsResult(result);
                    }

                    @Override
                    public void onNext(Result<List<String>> resultData) {
                        result.setCode(resultData.getCode());
                        result.setMsg(resultData.getMsg());
                        result.setData(resultData.getData());
                    }
                });
    }
}
