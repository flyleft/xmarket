package me.jcala.xmarket.mvp.user.team;

import android.os.Bundle;

import java.util.List;

import me.jcala.xmarket.R;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.mvp.a_base.BaseActivity;

public class UserTeamActivity extends BaseActivity implements UserTeamView{

    @Override
    protected void initViews(Bundle savedInstanceState) {
          setContentView(R.layout.user_team_activity);
    }

    @Override
    public void whenGetUserTeamSuccess(List<Team> teams) {

    }
}
