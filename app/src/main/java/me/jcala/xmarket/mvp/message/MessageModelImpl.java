package me.jcala.xmarket.mvp.message;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MessageModelImpl implements MessageModel{

    @Override
    public void executeConfirmDealReq(final onMessageListener listener,final Message newMsg,final Message old) {
        if (AppConf.useMock){
            return;
        }
        Result<MsgDto> result = new Result<MsgDto>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .hybridReq()
                .confirmDeal(newMsg.getId(),newMsg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<MsgDto>>() {
                    @Override
                    public void onCompleted() {
                        listener.onConfirmComplete(result,old);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onConfirmComplete(result,old);
                    }
                    @Override
                    public void onNext(Result<MsgDto> listResult) {
                        result.setCode(listResult.getCode());
                        result.setMsg(listResult.getMsg());
                        result.setData(listResult.getData());
                    }
                });

    }
}
