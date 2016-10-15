package me.jcala.xmarket.mvp.sort;


import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.SortTag;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SortTagModelImpl implements SortTagModel {
    @Override
    public void getSortTag(final OnGetSortTagListener listener) {
        ReqExecutor
                .INSTANCE()
                .allReq()
                .sortTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<SortTag>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(ApiConf.common_err);
                    }

                    @Override
                    public void onNext(Result<List<SortTag>> listResult) {
                        if (listResult.getCode()== ApiConf.req_success){
                            listener.onSuccess(listResult.getData());
                        }else {
                            listener.onFailure(listResult.getMsg());
                        }
                    }
                });
    }
}
