package me.jcala.xmarket.mvp.message;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.data.storage.UserIntermediate;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MessageService  extends Service {
    public static final String ACTION = "me.jcala.xmarket.mvp.message.MessageService";
    private Realm realm;
    private MessageModel model;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        realm=Realm.getDefaultInstance();
        model=new MessageModelImpl();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String userId= UserIntermediate.instance.getUser(this).getId();
        Observable.interval(1L, AppConf.Message_Interval, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Long aLong) ->{
                    int num=MessageIntermediate.instance.getNum();
                    model.executeMessageReq(null,num,userId,realm);
                });
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
