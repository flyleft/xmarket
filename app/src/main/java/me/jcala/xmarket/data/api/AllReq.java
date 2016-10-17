package me.jcala.xmarket.data.api;


import org.jetbrains.annotations.NotNull;
import java.util.List;
import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.entity.DealItem;
import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.SortTag;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface AllReq {
    /**
     * 用户登录请求
     */
    @NotNull
    @POST(ApiConf.login_url+"/{username}")
    Observable<Result<String>> login(
            @Path("username") @NotNull String username,
            @Query("password") @NotNull String password);

    /**
     * 用户注册请求
     */
    @NotNull
    @POST(ApiConf.register_url+"/{username}")
    Observable<Result<String>> register(
            @Path("username") @NotNull String username,
            @Query("phone") @NotNull String phone,
            @Query("password") @NotNull String password);

    /**
     * 获取分类列表请求
     */
    @NotNull
    @GET(ApiConf.sort_url)
    Observable<Result<List<SortTag>>> sortTag();

    /**
     *获取本校在售商品列表
     */
    @NotNull
    @GET(ApiConf.school_url+"/{school}/{page}")
    Observable<Result<List<DealItem>>> schoolDeals(
            @Path("username") @NotNull String school,
            @Path("page") int page
    );




}
