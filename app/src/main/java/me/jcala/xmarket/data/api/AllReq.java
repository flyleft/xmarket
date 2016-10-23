package me.jcala.xmarket.data.api;


import android.support.annotation.Nullable;

import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.entity.DealItem;
import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.SortTag;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface AllReq {

    /**
     * 用户登录请求
     */
    @POST(ApiConf.login_url+"/{username}")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<Result<String>> login(
            @Path("username")@Nullable String username,
            @Field("password")  String password);

    /**
     * 用户注册请求
     */
    @POST(ApiConf.register_url+"/{username}")
    Observable<Result<String>> register(
            @Path("username")  String username,
            @Field("phone")  String phone,
            @Field("password")  String password);

    /**
     * 获取分类列表请求
     */
    @GET(ApiConf.sort_url)
    Observable<Result<List<SortTag>>> sortTag();

    /**
     *获取本校在售商品列表
     */
    @GET(ApiConf.school_url+"/{school}/{page}")
    Observable<Result<List<DealItem>>> schoolDeals(
            @Path("school")  String school,
            @Path("page") int page
    );




}
