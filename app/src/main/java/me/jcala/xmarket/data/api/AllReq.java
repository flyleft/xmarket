package me.jcala.xmarket.data.api;

import android.database.Observable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import me.jcala.xmarket.conf.NetWorkConf;
import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.SortTag;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AllReq {
    @NotNull
    @GET(NetWorkConf.login_url)
    Observable<String> login(@Query("username") String username, @Query("password") String password);

    @GET(NetWorkConf.sort_url)
    io.reactivex.Observable<Result<List<SortTag>>> sortTag();
}
