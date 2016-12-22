package me.jcala.xmarket.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

import me.jcala.xmarket.data.pojo.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * SharedPreferences的一些操作
 * 用于存储用户信息，token值等
 */
public class SharedPreferencesStorage {

    private  final String SP="user";

    private static class TokenHolder{
        private static SharedPreferencesStorage tokenUtils=new SharedPreferencesStorage();
    }

    public static SharedPreferencesStorage instance=TokenHolder.tokenUtils;


    public void saveUser(final Context context, final User user){
        SharedPreferences sp = context.getSharedPreferences(SP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (user.getUsername()!=null){
            editor.putString("username",user.getUsername());
        }
        if (user.getId()!=null){
            editor.putString("userId",user.getId());
        }
        if (user.getAvatarUrl()!=null){
            editor.putString("userAvatar",user.getAvatarUrl());
        }
        if (user.getPassword()!=null){
            editor.putString("password",user.getPassword());
        }
        if (user.getPhone()!=null){
            editor.putString("phone",user.getPhone());
        }
        if (user.getSchool()!=null){
            editor.putString("school",user.getSchool());
        }
        editor.apply();
    }

     void clear(final Context context){
        SharedPreferences sp = context.getSharedPreferences(SP,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

     public User getUser(final Context context){
        SharedPreferences sp = context.getSharedPreferences(SP, MODE_PRIVATE);
        final User user=new User();
        user.setUsername(sp.getString("username",""));
        user.setPassword(sp.getString("password",""));
        user.setAvatarUrl(sp.getString("userAvatar",""));
        user.setSchool(sp.getString("school",""));
        user.setId(sp.getString("userId",""));
        user.setPhone(sp.getString("phone",""));
        return user;
    }

}
