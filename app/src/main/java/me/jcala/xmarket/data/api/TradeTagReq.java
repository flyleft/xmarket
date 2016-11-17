package me.jcala.xmarket.data.api;

import java.util.List;

import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;
import retrofit2.http.GET;
import rx.Observable;

public interface TradeTagReq {
    /**
     * 获取分类列表请求
     */
    @GET(ApiConf.get_trade_tag)
    Observable<Result<List<TradeTag>>> tradeTags();

}
