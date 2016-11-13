package me.jcala.xmarket.data.api;


import java.util.List;
import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;
import me.jcala.xmarket.data.pojo.User;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    Observable<Result<User>> register(
            @Field("username")  String username,
            @Field("password")  String password);

    /**
     * 获取分类列表请求
     */
    @GET(ApiConf.get_trade_sort)
    Observable<Result<List<TradeTag>>> sortTag();

    /**
     *获取本校在售商品列表
     */
    @GET(ApiConf.get_school_trades)
    Observable<Result<List<Trade>>> schoolDeals(
            @Path("school")  String school,
            @Path("page") int page
    );




}
