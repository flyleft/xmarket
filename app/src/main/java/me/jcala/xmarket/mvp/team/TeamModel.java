package me.jcala.xmarket.mvp.team;

import java.util.List;

import io.realm.Realm;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;

public interface TeamModel {

    interface onGainTeamListener{
        void onComplete(Result<List<Team>> result,Realm realm);
    }

    void executeGetTeamsReq(onGainTeamListener listener,
                            String schoolName,
                            int page,
                            Realm realm);
}
