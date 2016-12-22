package me.jcala.xmarket.util;

import android.content.Context;

import com.orhanobut.logger.Logger;

import me.jcala.xmarket.network.Api;
import me.jcala.xmarket.network.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.data.storage.UserIntermediate;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public enum  Interceptor {
    instance;

    public int tokenResultHandler(final Result<?> result,final Context context){
        if (result==null){
            return 0;
        }
        switch (result.getCode()) {
            case 100:
                return 1;
            case 99:
                return 0;
            default:
                return 0;
        }
    }

}
