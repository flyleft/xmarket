package me.jcala.xmarket.data.storage;

import android.content.Context;

import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.pojo.User;

/**
 * User,token信息存储的中介
 */
public enum UserIntermediate {
    instance;
    private User user;

    public User getUser(Context context) {
        if (user==null){
            user=SharedPreferencesStorage.instance.getUser(context);
        }
        return user;
    }

    public void logOut(Context context){
        this.user=null;
        SharedPreferencesStorage.instance.clear(context);
        ReqExecutor.INSTANCE().setToken("");
    }

}
