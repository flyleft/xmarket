package me.jcala.xmarket.mvp.user.team;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;

public interface UserTeamModel {

    interface onUserTeamListener{
        void onComplete(Result<List<Team>> result);
    }

    void executeUserTeamReq(onUserTeamListener listener,String userId);

}
