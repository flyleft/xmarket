package me.jcala.xmarket.data.api;

import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TradeTagReq {

     int GET_TAG=1;
     int GET_NAME=2;

    /**
     * 获取分类列表请求(List<TradeTag>)
     *
     * kind设置为1
     */
    @GET(ApiConf.get_trade_tag)
    Observable<Result<List<TradeTag>>> tradeTags(@Query("kind") int kind);


    /**
     * 获取分类名称列表请求(List<String>)
     *
     * kind设置为2
     */
    @GET(ApiConf.get_trade_tag)
    Observable<Result<List<String>>> tagNames(@Query("kind") int kind);

}
