package me.jcala.xmarket.data.api;


import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.data.pojo.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

public interface UserReq {

    /**
     * 用户登录并获取用户信息和token
     */
    @POST(ApiConf.auth)
    @FormUrlEncoded
    Observable<Result<User>> login(
            @Field("username") String username,
            @Field("password") String password);

    /**
     * 用户注册请求
     */
    @POST(ApiConf.register)
    @FormUrlEncoded
    Observable<Result<String>> register(
            @Field("username")  String username,
            @Field("password")  String password);


    /**
     * 注册下一步，设置学校和手机号
     */
    @PUT(ApiConf.register_next)
    @FormUrlEncoded
    Observable<Result<User>> registerNext(
            @Path("userId")  String user_id,
            @Field("phone")  String phone,
            @Field("school")  String school);

    /**
     * 发布商品
     */
    @Multipart
    @POST(ApiConf.create_trade)
    Observable<Result<String>> addTrade(
            // TODO: 16-12-10 没有根据服务器修改
            @Path("userId")String userId,
            @Part("trade") RequestBody trade,
            @Part List<MultipartBody.Part> parts);

    @PUT(ApiConf.confirm_deal)
    @FormUrlEncoded
    Observable<Result<MsgDto>> confirmDeal(
            @Path("userId")  String user_id,
            @Field("userId")  String otherUserId,
            @Field("tradeId")  String tradeId,
            @Field("id") String id);

    @POST(ApiConf.create_deal)
    Observable<Result<Message>> createDeal(
            @Path("userId") String userId,
            @Field("tradeId") String tradeId
    );
}
