package me.jcala.xmarket.util;

import android.content.Context;
import android.content.SharedPreferences;

import me.jcala.xmarket.data.pojo.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * 关于token的一些操作
 * 包括从token在sharepreference中的存取
 * 更新token
 */
public class TokenUtils {

    private  final String SP="user";

    private static class TokenHolder{
        private static TokenUtils tokenUtils=new TokenUtils();
    }

    public static TokenUtils instance=TokenHolder.tokenUtils;

    private void saveUser(final Context context,final String username,final String password){
        SharedPreferences sp = context.getSharedPreferences(SP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    private void saveToken(final Context context,final String token){
        SharedPreferences sp = context.getSharedPreferences(SP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", token);
        editor.apply();
    }

    private void clear(final Context context){
        SharedPreferences sp = context.getSharedPreferences(SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public String getToken(final Context context){
        SharedPreferences sp = context.getSharedPreferences(SP, MODE_PRIVATE);
        return sp.getString("token", "");
    }

    public User getUser(final Context context){
        SharedPreferences sp = context.getSharedPreferences(SP, MODE_PRIVATE);
        return new User(sp.getString("username",""),sp.getString("password",""));
    }

}
