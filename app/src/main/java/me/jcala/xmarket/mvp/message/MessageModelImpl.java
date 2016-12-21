package me.jcala.xmarket.mvp.message;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.network.Api;
import me.jcala.xmarket.network.ReqExecutor;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MessageModelImpl implements MessageModel{

    @Override
    public void executeConfirmDealReq(final OnMessageListener listener,final Message newMsg,final Message old) {
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

    @Override
    public void executeMessageReq(OnMessageListener listener, int num, String userId, Realm realmDefault) {
        Result<List<Message>> result = new Result<>();
        ReqExecutor
                .INSTANCE()
                .userReq()
                .getUserMsgs(userId,num,0,AppConf.size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<Message>>>() {
                    @Override
                    public void onCompleted() {
                        if (listener!=null){
                            listener.onHideRefresh();
                        }
                        List<Message> data=result.getData();
                        if (result.getCode()!=100||data==null){
                            return;
                        }
                        final RealmResults<Message> results = realmDefault.where(Message.class).findAll();
                        realmDefault.executeTransaction((Realm realm) -> results.deleteAllFromRealm());
                        realmDefault.executeTransaction((Realm realm) -> realm.copyToRealm(data));
                        MessageIntermediate.instance.setNum(data.size());
                        if (listener!=null){
                            listener.onGetMsgSuccess(data);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(Result<List<Message>> listResult) {
                        result.setCode(listResult.getCode());
                        result.setMsg(listResult.getMsg());
                        result.setData(listResult.getData());
                    }
                });
    }
}
