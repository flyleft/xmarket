package me.jcala.xmarket.mvp.team.add;

import me.jcala.xmarket.data.pojo.Team;

public interface TeamAddModel {
    interface OnTeamAddListener{
        void onComplete();
    }
    void executeTeamAddReq(OnTeamAddListener listener,String userId, Team team);
}
