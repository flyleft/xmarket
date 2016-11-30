package me.jcala.xmarket.mvp.user.trades.add;

import java.util.List;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.TradeTag;
import okhttp3.MultipartBody;

public interface TradeAddModel {

    interface onTradeAddListener{
        void hasGotAddTradeResult(Result<?> result);//执行发布商品请求，得到请求结果

        void hasGoTagsResult(Result<List<TradeTag>> result);//执行HTTP请求得到分类列表
    }

    void executeAddTradeReq(Trade trade,List<MultipartBody.Part> pics,onTradeAddListener listener);

    void executeGetTagsReq(onTradeAddListener listener);
}
