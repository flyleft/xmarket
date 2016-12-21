package me.jcala.xmarket.util;

import android.content.Context;

import com.orhanobut.logger.Logger;

import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.api.ReqExecutor;
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
            case 101:
                if (getToken(context)){
                    return 2;
                }else {
                    return 0;
                }
            default:
                return 0;
        }
    }

    /**
     * token过期后,自动登录，
     */
    private boolean getToken(final Context context){
        User user= UserIntermediate.instance.getUser(context);
        Result<User>  result=new Result<User>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .userReq()
                .login(user.getUsername(), user.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<User>>() {
                    @Override
                    public void onCompleted() {
                        String token=result.getData().getToken();
                        UserIntermediate.instance.updateToken(context,token);
                    }

                    @Override
                    public void onError(Throwable e) {
                        result.setCode(Api.SERVER_ERROR.code());
                        Logger.w("登录获取Token出错:"+e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Result<User> resultData) {
                        result.setCode(resultData.getCode());
                        result.setMsg(resultData.getMsg());
                        result.setData(resultData.getData());
                    }
                });
        return result.getCode()==100;
    }

}
