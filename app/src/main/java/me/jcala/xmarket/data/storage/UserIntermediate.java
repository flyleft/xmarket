package me.jcala.xmarket.data.storage;

import android.content.Context;

import com.orhanobut.logger.Logger;

import me.jcala.xmarket.data.api.ReqExecutor;
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
        ReqExecutor.INSTANCE().setToken("");
    }

    public void updateToken(final Context context,final String token){
        if (user==null){
            user=SharedPreferencesStorage.instance.getUser(context);
        }
        SharedPreferencesStorage.instance.updateToken(context,token);
        user.setToken(token);
    }
}
