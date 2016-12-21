package me.jcala.xmarket.mvp.team;

import java.util.List;

import io.realm.Realm;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.network.Api;
import me.jcala.xmarket.network.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.mock.TeamMock;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TeamModelImpl implements TeamModel{

    @Override
    public void executeGetTeamsReq(final onGainTeamListener listener,
                                   final String schoolName,
                                   int page,final Realm realm) {
        if (AppConf.useMock){
            listener.onComplete(new TeamMock().gainTeamList(),realm);
            return;
        }
        Result<List<Team>> result = new Result<List<Team>>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .hybridReq()
                .getTeams(schoolName,0,page,AppConf.size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<Team>>>() {
                    @Override
                    public void onCompleted() {
                        listener.onComplete(result,realm);
                    }

                    @Override
                    public void onError(Throwable e) {
                        result.setCode(Api.SERVER_ERROR.code());
                        listener.onComplete(result,realm);
                    }
                    @Override
                    public void onNext(Result<List<Team>> listResult) {
                        result.setCode(listResult.getCode());
                        result.setMsg(listResult.getMsg());
                        result.setData(listResult.getData());
                    }
                });

    }

}
