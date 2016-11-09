package me.jcala.xmarket.mvp.sort;

import java.util.List;

import me.jcala.xmarket.data.pojo.TradeTag;

interface SortTagModel {
    interface onGainListener {
        void onSuccess(List<TradeTag> tradeTagList);
        void onFailure(String errMsg);
    }
    void getSortTag(final onGainListener listener);
}
