package me.jcala.xmarket.mvp.user.team;

import java.util.List;

import me.jcala.xmarket.data.pojo.Team;

public interface UserTeamView {

    void whenGetUserTeamSuccess(List<Team> teams);
}
