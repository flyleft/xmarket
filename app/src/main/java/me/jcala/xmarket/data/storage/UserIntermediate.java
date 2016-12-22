package me.jcala.xmarket.data.storage;

import android.content.Context;

import me.jcala.xmarket.network.ReqExecutor;
import me.jcala.xmarket.data.pojo.User;

/**
 * User,token信息存储的中介
 */
public enum UserIntermediate {
    instance, Interceptor;
    private User user;

    public User getUser(final Context context) {
        if (user==null){
            user=SharedPreferencesStorage.instance.getUser(context);
        }
        return user;
    }

    public void logOut(final Context context){
        this.user=null;
        SharedPreferencesStorage.instance.clear(context);
    }
}
