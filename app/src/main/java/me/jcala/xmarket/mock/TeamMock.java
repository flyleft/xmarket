package me.jcala.xmarket.mock;

import java.util.ArrayList;
import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;

public class TeamMock {

    public Result<List<Team>> gainTeamList(){
        Team team1=new Team("轮滑社","回收轮滑鞋","https://jcalaz.github.io/img/sort_team_lunhua.jpg");
        Team team2=new Team("学生会","补助山区贫困生","https://jcalaz.github.io/img/sort_team_students.jpg");
        Team team3=new Team("健身社","回收健身器材给学弟学妹","https://jcalaz.github.io/img/sort_team_body.jpg");
        List<Team> teamList=new ArrayList<>();
        teamList.add(team1);
        teamList.add(team2);
        teamList.add(team3);
        Result<List<Team>> result=new Result<>();
        result.setCode(100);
        result.setData(teamList);
        result.setMsg("");
        return result;
    }

}
