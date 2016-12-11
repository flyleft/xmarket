package me.jcala.xmarket.data.api;

import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.data.pojo.Trade;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface HybridReq {

    //获取所有学校名称列表
    @GET(ApiConf.get_schools)
    Observable<Result<List<String>>> getSchoolNames();

    //获取本校在售商品列表
    @GET(ApiConf.get_school_trades)
    Observable<Result<List<Trade>>> getSchoolTrades(
            @Path("schoolName")  String school,
            @Path("page") int page
    );

    //根据学校名获取该学校的所有志愿队列表
    @GET(ApiConf.get_school_teams)
    Observable<Result<List<Team>>> getTeams(
            @Path("schoolName") String schoolName
    );

    //创建交易(发起购买商品请求)
    @POST(ApiConf.create_deal)
    Observable<Result<String>> createDeal(
            @Field("fromId") String fromId,
            @Field("fromName") String fromName,
            @Field("fromAvatar") String fromAvatar,
            @Field("tradeId") String tradeId
    );
}
