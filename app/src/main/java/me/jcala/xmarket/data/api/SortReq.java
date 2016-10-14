package me.jcala.xmarket.data.api;

import java.util.List;

import io.reactivex.Observable;
import me.jcala.xmarket.conf.NetWorkConf;
import me.jcala.xmarket.data.entity.Result;
import me.jcala.xmarket.data.entity.SortTag;
import retrofit2.http.GET;

public interface SortReq {
    @GET(NetWorkConf.sort_url)
    Observable<Result<List<SortTag>>> sortTag();
}