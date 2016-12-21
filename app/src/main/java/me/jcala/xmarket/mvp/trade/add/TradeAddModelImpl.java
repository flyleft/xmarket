package me.jcala.xmarket.mvp.trade.add;

import java.util.List;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.network.Api;
import me.jcala.xmarket.network.ReqExecutor;
import me.jcala.xmarket.network.TradeReq;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.mock.TradeMock;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TradeAddModelImpl implements TradeAddModel{

    @Override
    public void executeAddTradeReq(final RequestBody tradeJson,
                                   final List<MultipartBody.Part> pics, final onTradeAddListener listener) {
        Result<String> result= new Result<String>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .tradeReq()
                .addTrade(tradeJson,pics)
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
        Result<List<String>> result= new Result<List<String>>().api(Api.SERVER_ERROR);

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
