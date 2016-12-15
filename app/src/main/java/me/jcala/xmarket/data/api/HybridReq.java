package me.jcala.xmarket.data.api;

import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.data.pojo.Team;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.User;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface HybridReq {

    //获取所有学校名称列表
    @GET(ApiConf.get_schools)
    Observable<Result<List<String>>> getSchoolNames();


    //根据学校名获取该学校的所有志愿队列表
    @GET(ApiConf.get_school_teams)
    Observable<Result<List<Team>>> getTeams(
            @Path("schoolName") String schoolName
    );

    //创建交易(发起购买商品请求)
    @POST(ApiConf.create_deal)
    @FormUrlEncoded
    Observable<Result<String>> createDeal(
            @Field("fromId") String fromId,
            @Field("fromName") String fromName,
            @Field("fromAvatar") String fromAvatar,
            @Field("tradeId") String tradeId
    );
    @POST(ApiConf.confirm_deal)
    @FormUrlEncoded
    Observable<Result<MsgDto>> confirmDeal(
            @Path("messageId")  String messageId,
            @Field("message") Message message
    );
}
