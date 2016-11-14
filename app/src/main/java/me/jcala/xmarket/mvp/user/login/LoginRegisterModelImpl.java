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
    public void loginRequest(final String username,final String password,final onLoginRegisterListener listener) {
        @SuppressWarnings("unchecked")
        Result<User>  result=CommonFactory.INSTANCE().server_error();
        ReqExecutor
                .INSTANCE()
                .allReq()
                .login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<User>>() {
                    @Override
                    public void onCompleted() {
                        listener.loginComplete(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loginComplete(result);
                    }

                    @Override
                    public void onNext(Result<User> resultData) {
                        result.setCode(resultData.getCode());
                        result.setMsg(resultData.getMsg());
                        result.setData(resultData.getData());
                    }
                });
    }

    @Override
    public void registerRequest(final String username,final String password,final onLoginRegisterListener listener) {
        @SuppressWarnings("unchecked")
        Result<User>  result=CommonFactory.INSTANCE().server_error();

        ReqExecutor
                .INSTANCE()
                .allReq()
                .register(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<User>>() {
                    @Override
                    public void onCompleted() {
                        listener.registerComplete(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loginComplete(result);
                    }

                    @Override
                    public void onNext(Result<User> resultData) {
                        result.setCode(resultData.getCode());
                        result.setMsg(resultData.getMsg());
                        result.setData(resultData.getData());
                    }
                });
    }

}
