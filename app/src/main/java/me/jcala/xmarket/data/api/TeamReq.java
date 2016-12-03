package me.jcala.xmarket.data.api;

import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Team;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface TeamReq {

    /**
     * 根据学校名获取该学校的所有志愿队列表
     */
    @GET(ApiConf.get_school_teams)
    Observable<Result<List<Team>>> getTeams(
            @Path("schoolName") String schoolName
    );
}
