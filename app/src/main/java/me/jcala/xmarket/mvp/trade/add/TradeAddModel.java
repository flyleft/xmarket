package me.jcala.xmarket.mvp.trade.add;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface TradeAddModel {

    interface onTradeAddListener{
        void hasGotAddTradeResult(Result<String> result);//执行发布商品请求，得到请求结果

        void hasGoTagsResult(Result<List<String>> result);//执行HTTP请求得到分类名称列表
    }

    void executeAddTradeReq(RequestBody tradeJson,
                            List<MultipartBody.Part> pics, onTradeAddListener listener);

    void executeGetTagsReq(onTradeAddListener listener);
}
