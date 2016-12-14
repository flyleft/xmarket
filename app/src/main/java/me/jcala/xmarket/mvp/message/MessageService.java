package me.jcala.xmarket.mvp.message;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.main.MainActivity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MessageService  extends Service {
    public static final String ACTION = "me.jcala.xmarket.mvp.message.MessageService";

    private Notification.Builder mNotification;
    private NotificationManager mManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int icon = R.drawable.ic_launcher;
        mNotification = new Notification.Builder(this)
                .setSmallIcon(icon)
                .setContentTitle("xmarket")
                .setContentText("收到一条消息");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Observable.interval(8, 8, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Logger.e("弹出Notification......"+System.currentTimeMillis()/1000);
                    }
                });

        return START_REDELIVER_INTENT;
    }

    //弹出Notification
    private void showNotification() {
        mNotification.setWhen(System.currentTimeMillis());
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,Intent.FILL_IN_ACTION);
        mNotification.setContentIntent(pendingIntent);
        mManager.notify(0, mNotification.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service:onDestroy");
    }

}
