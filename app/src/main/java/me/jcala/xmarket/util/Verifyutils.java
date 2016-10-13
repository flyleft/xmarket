package me.jcala.xmarket.util;


import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

public class Verifyutils {
    private static final String TAG="xmarket_verifyutils";
    public String getTokenRequest(Context ctx){
        String tokenId= null;
        try {
            tokenId = getDeviceID(ctx)+getMac(ctx);
        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
        }
        return tokenId;
    }
    //获取设备ID
    private String getDeviceID(Context ctx) throws Exception {
        String strResult = null;
        TelephonyManager telephonyManager = (TelephonyManager) ctx
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            strResult = telephonyManager.getDeviceId();
        }
        if (strResult == null) {
            strResult = Settings.Secure.getString(ctx.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }
        return strResult;
    }
    //获取mac地址
    private String getMac(Context ctx) throws Exception{
        WifiManager wifiManager = (WifiManager) ctx
                .getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wi = wifiManager.getConnectionInfo();
            return wi.getMacAddress();
        }
        return null;
    }

}
