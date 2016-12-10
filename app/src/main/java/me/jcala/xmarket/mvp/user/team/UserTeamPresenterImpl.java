package me.jcala.xmarket.mvp.user.team;

import android.content.Context;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;

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

    @Override
    public void gainUserTeamList() {

    }

    @Override
    public void onComplete(Result<List<Team>> result) {

    }

    @Override
    public void onFail(String errorMsg) {

    }
}
