package me.jcala.xmarket.mvp.user.login;


import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.util.CommonFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class LoginRegisterModelImpl implements LoginRegisterModel {

    @Override
    public Result<String> loginRequest(User user) {
        Result<String>  result= CommonFactory.INSTANCE().errorResult();
        ReqExecutor
                .INSTANCE()
                .allReq()
                .login(user.getUsername(),user.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<String>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Result<String> resultData) {
                        result.setCode(resultData.getCode());
                        result.setMsg(resultData.getMsg());
                        result.setData(resultData.getData());
                    }
                });
        return result;
    }

    @Override
    public Result<String> registerRequest(User user) {
        ReqExecutor
                .INSTANCE()
                .allReq()
                .register(user.getUsername(),user.getPhone(),user.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<String>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Result<String> resultData) {
                    }
                });
        return null;
    }
}
