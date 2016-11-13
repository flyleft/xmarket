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


   /* private void saveToken(final Context context,final String token){
        SharedPreferences sp = context.getSharedPreferences(SP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", token);
        editor.apply();
    }*/

    public void saveUserToken(final Context context,final User user,final String token){
        SharedPreferences sp = context.getSharedPreferences(SP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", token);
        if (user.getUsername()!=null){
            editor.putString("username",user.getUsername());
        }
        if (user.getId()!=null){
            editor.putString("user_id",user.getId());
        }
        if (user.getAvatar_url()!=null){
            editor.putString("user_avatar",user.getAvatar_url());
        }
        if (user.getPassword()!=null){
            editor.putString("password",user.getPassword());
        }
        if (user.getPhone()!=null){
            editor.putString("phone",user.getPhone());
        }
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
