package me.jcala.xmarket.data.api;

import io.reactivex.Observable;
import me.jcala.xmarket.conf.NetWorkConf;
import me.jcala.xmarket.data.entity.Result;
import retrofit2.http.GET;

interface UserReq {
    @GET(NetWorkConf.login_url)
    Observable<Result<String>> login();
}