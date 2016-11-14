package me.jcala.xmarket.data.api;

import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.User;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface SchoolReq {

    /**
     * 获取所有学校名称列表
     */
    @GET(ApiConf.get_school_list)
    Observable<Result<User>> login();

    /**
     *获取本校在售商品列表
     */
    @GET(ApiConf.get_school_trades)
    Observable<Result<List<Trade>>> schoolDeals(
            @Path("school")  String school,
            @Path("page") int page
    );
}
