package me.jcala.xmarket.mvp.message;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.storage.UserIntermediate;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MessageService  extends Service {
    public static final String ACTION = "me.jcala.xmarket.mvp.message.MessageService";

//    private Notification.Builder mNotification;
//    private NotificationManager mManager;
    final Intent intent = new Intent();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        intent.setAction(MessageFragment.ACTION_UPDATE_UI);

       /* mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int icon = R.drawable.ic_launcher;
        mNotification = new Notification.Builder(this)
                .setSmallIcon(icon)
                .setContentTitle("xmarket")
                .setContentText("收到一条消息");*/
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Observable.interval(3, AppConf.Message_Interval, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Long aLong) ->{
                     String userId= UserIntermediate.instance.getUser(this).getId();
                     int num=MessageIntermediate.instance.getMessageList().size();
                    execute(userId,num);
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
                .getUserMsgs(userId,num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<MsgDto>>() {
                    @Override
                    public void onCompleted() {
                        if (result.getCode()!=100||result.getData()==null){
                            return;
                        }

                        int oldSize=MessageIntermediate.instance.getMessageList().size();
                        int newSize=result.getData().getAllNum();
                        if (newSize >= oldSize){
                            sendBroadcast(intent);
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

    //弹出Notification
   /* private void showNotification() {
        mNotification.setWhen(System.currentTimeMillis());
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,Intent.FILL_IN_ACTION);
        mNotification.setContentIntent(pendingIntent);
        mManager.notify(0, mNotification.build());
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
