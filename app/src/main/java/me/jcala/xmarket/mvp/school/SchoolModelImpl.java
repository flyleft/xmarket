package me.jcala.xmarket.mvp.school;

import java.util.List;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.mock.TradeMock;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class SchoolModelImpl implements SchoolModel{

    @Override
    public void executeGetTradesReq(onGainListener listener,String schoolName,int page) {
        if (AppConf.useMock){
            listener.onReqComplete(new TradeMock().gainSchoolTrades());
            return;
        }
        Result<List<Trade>> result = new Result<List<Trade>>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .tradeReq()
                .getSchoolTrades(schoolName,page,AppConf.size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<Trade>>>() {
                    @Override
                    public void onCompleted() {
                        listener.onReqComplete(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onReqComplete(result);
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
