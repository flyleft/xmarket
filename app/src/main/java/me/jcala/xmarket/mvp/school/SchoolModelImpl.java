package me.jcala.xmarket.mvp.school;

import java.util.List;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.mock.TradeMock;
import me.jcala.xmarket.util.CommonFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class SchoolModelImpl implements SchoolModel{

    @Override
    @SuppressWarnings("unchecked")
    public void getSchoolTrades(onGainListener listener,String schoolName,int page) {
        if (AppConf.useMock){
            listener.onComplete(new TradeMock().gainSchoolTrades());
            return;
        }
        Result result = CommonFactory.INSTANCE().server_error();
        ReqExecutor
                .INSTANCE()
                .schoolReq()
                .getSchoolTrades(schoolName,page)
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
