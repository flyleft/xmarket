package me.jcala.xmarket.network;

import java.util.List;

import me.jcala.xmarket.network.ApiConf;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.data.pojo.Team;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface HybridReq {

    //创建志愿队
    @Multipart
    @POST(ApiConf.create_team)
    Observable<Result<String>> createTeam(
            @Part("team") RequestBody team,
            @Part List<MultipartBody.Part> parts);

    //获取所有学校名称列表
    @GET(ApiConf.get_schools)
    Observable<Result<List<String>>> getSchoolNames();


    //根据学校名获取该学校的所有志愿队列表
    @GET(ApiConf.get_school_teams)
    Observable<Result<List<Team>>> getTeams(
            @Path("schoolName") String schoolName,
            @Query("type") int type,
            @Query("page") int page,
            @Query("size") int size
    );

    @GET(ApiConf.get_school_teams)
    Observable<Result<List<String>>> getTeamNames(
            @Path("schoolName") String schoolName,
            @Query("type") int type,
            @Query("page") int page,
            @Query("size") int size
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
    //确认交易
    @POST(ApiConf.confirm_deal)
    @FormUrlEncoded
    Observable<Result<MsgDto>> confirmDeal(
            @Path("messageId")  String messageId,
            @Field("message") Message message
    );
}
