package me.jcala.xmarket.mvp.user.login.register.next;

import java.util.List;

import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.util.CommonFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterNextModelImpl implements RegisterNextModel{

    @Override
    public void executeSchoolRequest(final onRegisterNextListener listener) {
        @SuppressWarnings("unchecked")
        Result<List<String>> result= CommonFactory.INSTANCE().server_error();
        ReqExecutor
                .INSTANCE()
                .schoolReq()
                 .getSchool()
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
    public void executeSchoolRequest(final String userId,final String phone,
                                     final String school,final onRegisterNextListener listener) {
        @SuppressWarnings("unchecked")
        Result<User> result= CommonFactory.INSTANCE().server_error();
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
