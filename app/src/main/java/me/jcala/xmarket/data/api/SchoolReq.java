package me.jcala.xmarket.data.api;

import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface SchoolReq {

    /**
     * 获取所有学校名称列表
     */
    @GET(ApiConf.get_school_list)
    Observable<Result<List<String>>> getSchoolNames();

    /**
     *获取本校在售商品列表
     */
    @GET(ApiConf.get_school_trades)
    Observable<Result<List<Trade>>> getSchoolTrades(
            @Path("school")  String school,
            @Path("page") int page
    );
}
