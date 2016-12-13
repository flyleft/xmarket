package me.jcala.xmarket.mvp.message;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.main.MainActivity;

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
        Logger.i("开始轮询服务。。。。");
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int icon = R.drawable.ic_launcher;
        mNotification = new Notification.Builder(this)
                .setSmallIcon(icon)
                .setContentTitle("xmarket")
                .setContentText("收到一条消息");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new PollingThread().start();
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


    int count = 0;
    class PollingThread extends Thread {
        @Override
        public void run() {
            System.out.println("Polling...");
            count ++;
            //当计数能被5整除时弹出通知
            if (count % 5 == 0) {
                showNotification();
                System.out.println("New message!");
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service:onDestroy");
    }

}
