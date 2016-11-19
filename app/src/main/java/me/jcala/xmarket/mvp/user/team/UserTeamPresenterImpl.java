package me.jcala.xmarket.mvp.user.team;

import android.content.Context;

public class UserTeamPresenterImpl
        implements UserTeamPresenter,UserTeamModel.onUserTeamListener{
    private Context context;
    private UserTeamView view;
    private UserTeamModel model;

    public UserTeamPresenterImpl(Context context, UserTeamView view) {
        this.context = context;
        this.view = view;
        this.model=new UserTeamModelImpl();
    }
}
