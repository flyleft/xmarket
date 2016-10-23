package me.jcala.xmarket.mvp.team;

import java.util.List;

import me.jcala.xmarket.data.pojo.Team;

public interface TeamModel {
    interface onGainTeamListener{
        void onSuccess(List<Team> teamList);
        void onFailure();
    }
    void getTeams(final onGainTeamListener listener);
}
