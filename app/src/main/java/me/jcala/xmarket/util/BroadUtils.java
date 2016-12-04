package me.jcala.xmarket.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class BroadUtils extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            if (isWifiNetConnected(context)) {
                Toast.makeText(context, "连接上wifi了", Toast.LENGTH_SHORT);
            } else if (isPhoneNetConnected(context)) {
                Toast.makeText(context, "连接上手机网络了", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "无网络连接", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static boolean isNetConnected(Context context) {
        boolean ret = false;
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            return ret;
        }
        ret = networkInfo.isAvailable() & networkInfo.isConnected();
        return ret;
    }
    public static boolean isPhoneNetConnected(Context context) {
        int typeMobile = ConnectivityManager.TYPE_MOBILE;//手机网络类型
        return isNetworkConnected(context, typeMobile);
    }
    public static boolean isWifiNetConnected(Context context) {
        int typeMobile = ConnectivityManager.TYPE_WIFI;//WIFI网络类型
        return isNetworkConnected(context, typeMobile);
    }
    private static boolean isNetworkConnected(Context context, int typeMobile) {
        boolean ret = false;
        if (!isNetConnected(context)) {
            return ret;
        }
        ConnectivityManager connectManger = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectManger.getNetworkInfo(typeMobile);
        if (networkInfo == null) {
            return ret;
        }
        return ret;
    }
}
