package me.jcala.xmarket.mvp.team;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;

public interface TeamModel {

    interface onGainTeamListener{
        void onComplete(Result<List<Team>> result);
        void onFail(String errorMsg);
    }

    void getTeams(onGainTeamListener listener,String schoolName);
}
