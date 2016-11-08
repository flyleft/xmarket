package me.jcala.xmarket.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * 关于token的一些操作
 * 包括从token在sharepreference中的存取
 * 更新token
 */
public class TokenUtils {

    private final String SP="token";

    private final String key="jwt";

    private String token="";//用于存储token的值

    private static class TokenHolder{
        private static TokenUtils tokenUtils=new TokenUtils();
    }

    public static TokenUtils instance=TokenHolder.tokenUtils;

    private void saveData(final Context context,final String string){
        SharedPreferences sp = context.getSharedPreferences(SP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, string);
        editor.apply();
    }

    private void clear(Context context){
        SharedPreferences sp = context.getSharedPreferences(SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    private String getToken(Context context){
        SharedPreferences sp = context.getSharedPreferences(SP, MODE_PRIVATE);
        return sp.getString(key, "");
    }
}
