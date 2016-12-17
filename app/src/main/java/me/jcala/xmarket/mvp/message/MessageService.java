package me.jcala.xmarket.mvp.message;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.mvp.main.MainPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MessageService  extends Service {
    public static final String ACTION = "me.jcala.xmarket.mvp.message.MessageService";
    final Intent messageIntent = new Intent();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        messageIntent.setAction(MessageFragment.ACTION_UPDATE_UI);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Observable.interval(3, AppConf.Message_Interval, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Long aLong) ->{
                     String userId= UserIntermediate.instance.getUser(this).getId();
                    List<Message> list=MessageIntermediate.instance.getMessageList();
                    if (list!=null){
                        int num=list.size();
                        execute(userId,num);
                    }
                });
        return START_NOT_STICKY;
    }
    private void execute(String userId,int num){
        if (AppConf.useMock){
            return;
        }
        Result<MsgDto> result = new Result<>();
        ReqExecutor
                .INSTANCE()
                .userReq()
                .getUserMsgs(userId,num,0,AppConf.size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<MsgDto>>() {
                    @Override
                    public void onCompleted() {

                        if (result.getCode()!=100||result.getData().getMsgs()==null){
                            return;
                        }

                        MessageIntermediate intermediate=MessageIntermediate.instance;
                        int oldSize=intermediate.getNum();
                        int newSize=result.getData().getAllNum();
                        if (newSize >= oldSize){
                            intermediate.setNum(newSize);
                            intermediate.setMessageList(result.getData().getMsgs());
                            sendBroadcast(messageIntent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

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
    public void onDestroy() {
        super.onDestroy();
    }

}
