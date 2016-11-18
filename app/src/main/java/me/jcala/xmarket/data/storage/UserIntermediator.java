package me.jcala.xmarket.data.storage;

import android.content.Context;

import me.jcala.xmarket.data.pojo.User;

/**
 * User,token信息存储的中介
 */
public enum  UserIntermediator {
    instance;
    private User user;

    public User getUser(Context context) {
        if (user==null){
            user=SharedPreferencesStorage.instance.getUser(context);
        }
        return user;
    }


}
