package me.jcala.xmarket.mvp.team.trade;

import android.content.Context;

public class TeamTradePresenterImpl implements TeamTradePresenter{
    private Context context;
    private TeamTradeView view;
    private TeamTradeModel model;

    public TeamTradePresenterImpl(Context context, TeamTradeView view) {
        this.context = context;
        this.view = view;
        this.model=new TeamTradeModelImpl();
    }

    @Override
    public void initView(String teamName) {

    }
}
