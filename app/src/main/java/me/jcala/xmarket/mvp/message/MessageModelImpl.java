package me.jcala.xmarket.mvp.message;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.mock.MessageMock;
import me.jcala.xmarket.util.CommonFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MessageModelImpl implements MessageModel{

    @Override
    public void executeMsgReq(final onMessageListener listener, int num) {
       if (AppConf.useMock){
           listener.onComplete(new MessageMock().gainMsg());
           return;
       }
        Result result = CommonFactory.INSTANCE().server_error();

    }

    @Override
    public void executeConfirmDealReq(final onMessageListener listener,String myId,
                                      final String userId,final String tradeId) {
        if (AppConf.useMock){
            listener.onComplete(new MessageMock().gainMsg());
            return;
        }
        Result result = CommonFactory.INSTANCE().server_error();
        ReqExecutor
                .INSTANCE()
                .userReq()
                .confirmDeal(myId,userId,tradeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<MsgDto>>() {
                    @Override
                    public void onCompleted() {
                        listener.onComplete(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onComplete(result);
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
