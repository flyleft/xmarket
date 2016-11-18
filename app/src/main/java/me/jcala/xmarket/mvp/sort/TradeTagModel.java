package me.jcala.xmarket.mvp.sort;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;

interface TradeTagModel {
    interface onGainListener {
        void onComplete(Result<List<TradeTag>> result);
        void onFail(String msg);
    }
    void getSortTag(final onGainListener listener);
}
