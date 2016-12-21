package me.jcala.xmarket.mvp.team.add;


import java.util.List;

import me.jcala.xmarket.network.Api;
import me.jcala.xmarket.network.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TeamAddModelImpl implements TeamAddModel{

    @Override
    public void executeTeamAddReq(final OnTeamAddListener listener,
                                  final RequestBody team,
                                  final List<MultipartBody.Part> pics) {
        Result<String> result= new Result<String>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .hybridReq()
                .createTeam(team,pics)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<String>>() {
                    @Override
                    public void onCompleted() {
                        listener.onComplete(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        result.setCode(Api.SERVER_ERROR.code());
                        listener.onComplete(result);
                    }

                    @Override
                    public void onNext(Result<String> resultData) {
                        result.setCode(resultData.getCode());
                        result.setMsg(resultData.getMsg());
                    }
                });
    }

}
