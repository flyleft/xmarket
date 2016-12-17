package me.jcala.xmarket.mvp.team;

import java.util.List;
import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.conf.Api;
import me.jcala.xmarket.data.api.ReqExecutor;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.mock.TeamMock;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TeamModelImpl implements TeamModel{

    @Override
    public void getTeams(final onGainTeamListener listener,final String schoolName,int page) {
        if (AppConf.useMock){
            listener.onComplete(new TeamMock().gainTeamList());
            return;
        }
        Result<List<Team>> result = new Result<List<Team>>().api(Api.SERVER_ERROR);
        ReqExecutor
                .INSTANCE()
                .hybridReq()
                .getTeams(schoolName,page,AppConf.size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<Team>>>() {
                    @Override
                    public void onCompleted() {
                        listener.onComplete(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(Api.SERVER_ERROR.msg());
                    }
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onNext(Result<List<Team>> listResult) {
                        result.setCode(listResult.getCode());
                        result.setMsg(listResult.getMsg());
                        result.setData(listResult.getData());
                    }
                });

    }

}
