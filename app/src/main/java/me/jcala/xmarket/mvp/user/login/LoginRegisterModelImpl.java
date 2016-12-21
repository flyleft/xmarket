package me.jcala.xmarket.mvp.user.login;


import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.network.Api;
import me.jcala.xmarket.network.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.mock.UserInfoMock;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class LoginRegisterModelImpl implements LoginRegisterModel {

    @Override
    public void loginRequest(final String username,final String password,final onLoginRegisterListener listener) {
        if (AppConf.useMock){
            listener.loginComplete(new UserInfoMock().loginOrRegisterNext());
            return;
        }
        Result<User>  result=new Result<User>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .userReq()
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

        if (AppConf.useMock){
            listener.registerComplete(new UserInfoMock().register());
            return;
        }

        Result<String>  result = new Result<String>().api(Api.SERVER_ERROR);

        ReqExecutor
                .INSTANCE()
                .userReq()
                .register(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<String>>() {
                    @Override
                    public void onCompleted() {
                        listener.registerComplete(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.registerComplete(result);
                    }

                    @Override
                    public void onNext(Result<String> resultData) {
                        result.setCode(resultData.getCode());
                        result.setMsg(resultData.getMsg());
                        result.setData(resultData.getData());
                    }
                });
    }

}
