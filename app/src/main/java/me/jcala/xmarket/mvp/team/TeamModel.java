package me.jcala.xmarket.mvp.team;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;

public interface TeamModel {

    interface onGainTeamListener{
        void onSuccess(Result<List<Team>> result);
        void onFail();
    }

    void getTeams(final onGainTeamListener listener);
}
