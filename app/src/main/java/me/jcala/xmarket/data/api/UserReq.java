package me.jcala.xmarket.data.api;


import android.database.Observable;

import me.jcala.xmarket.conf.NetWorkConf;
import retrofit2.http.GET;

public interface UserReq {
    @GET(NetWorkConf.login_url)
    Observable<String> login();
}
