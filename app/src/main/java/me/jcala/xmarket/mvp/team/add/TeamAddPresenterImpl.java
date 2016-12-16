package me.jcala.xmarket.mvp.team.add;

import android.content.Context;

public class TeamAddPresenterImpl implements TeamAddPresenter,TeamAddModel.OnTeamAddListener{
    private Context context;
    private TeamAddView view;
    private TeamAddModel model;

    public TeamAddPresenterImpl(Context context, TeamAddView view) {
        this.context = context;
        this.view = view;
        this.model=new TeamAddModelImpl();
    }

    @Override
    public void onComplete() {

    }
}
