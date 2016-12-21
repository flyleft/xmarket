package me.jcala.xmarket.mvp.user.login.register.next;

import java.util.List;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.network.Api;
import me.jcala.xmarket.network.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.mock.UserInfoMock;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterNextModelImpl implements RegisterNextModel{

    @Override
    public void executeSchoolRequest(final onRegisterNextListener listener) {

        if(AppConf.useMock){
            listener.hasGotSchoolList(new UserInfoMock().gainSchoolList());
            return;
        }

        Result<List<String>> result= new Result<List<String>>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .hybridReq()
                 .getSchoolNames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<String>>>() {
                    @Override
                    public void onCompleted() {
                        listener.hasGotSchoolList(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.hasGotSchoolList(result);
                    }

                    @Override
                    public void onNext(Result<List<String>> resultData) {
                        result.setCode(resultData.getCode());
                        result.setMsg(resultData.getMsg());
                        result.setData(resultData.getData());
                    }
                });
    }

    @Override
    public void executeRegisterRequest(final String userId,final String phone,
                                     final String school,final onRegisterNextListener listener) {
        if(AppConf.useMock){
            listener.hasGotRegisterResult(new UserInfoMock().loginOrRegisterNext());
            return;
        }

        Result<User> result= new Result<User>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .userReq()
                .registerNext(userId,phone,school)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<User>>() {
                    @Override
                    public void onCompleted() {
                        listener.hasGotRegisterResult(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.hasGotRegisterResult(result);
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
