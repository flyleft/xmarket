package me.jcala.xmarket.data.api;


import org.jetbrains.annotations.NotNull;

import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.SortTag;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface AllReq {
    @NotNull
    @GET(ApiConf.login_url)
    Observable<String> login(
            @Query("username") @NotNull String username,
            @Query("password") @NotNull String password);

    @NotNull
    @GET(ApiConf.sort_url)
    Observable<Result<List<SortTag>>> sortTag();
}
