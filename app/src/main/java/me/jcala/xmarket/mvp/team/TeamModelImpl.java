package me.jcala.xmarket.mvp.team;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.mock.TeamMock;

public class TeamModelImpl implements TeamModel{

    @Override
    public void getTeams(final onGainTeamListener listener) {
        if (AppConf.useMock){
            listener.onSuccess(new TeamMock().gainTeamList());
            return;
        }

    }

}
