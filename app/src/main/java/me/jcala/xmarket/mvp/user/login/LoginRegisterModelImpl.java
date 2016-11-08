package me.jcala.xmarket.mvp.user.login;



import java.util.List;

import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.SortTag;
import me.jcala.xmarket.data.pojo.User;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class LoginRegisterModelImpl implements LoginRegisterModel {
    @Override
    public Result<String> loginRequest(User userBean) {
        ReqExecutor
                .INSTANCE()
                .allReq()
                .sortTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<SortTag>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Result<List<SortTag>> listResult) {

                    }
                });
        return null;
    }

    @Override
    public Result<String> registerRequest(User user) {
        ReqExecutor
                .INSTANCE()
                .allReq()
                .sortTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<SortTag>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Result<List<SortTag>> listResult) {
                    }
                });
        return null;
    }

/*    private Result<String> executeLoginLocal(User userBean){
        Result<String> result=new Result<>();
        if ("jcala".equals(userBean.getUsername())&&"123".equals(userBean.getPassword())){
            result.setCode(1);
        }else if ("jcala".equals(userBean.getUsername())){
            result.setCode(0);
        }else{
            result.setCode(2);
        }
        return result;
    }
    private Result<String> executeRegisterLocal(User userBean){
        Result<String> result=new Result<>();
        if ("jcala".equals(userBean.getUsername())){
            result.setCode(2);
        }else if ("187".equals(userBean.getPhone())){
            result.setCode(3);
        }else{
            result.setCode(1);
        }
        return result;
    }*/

}
