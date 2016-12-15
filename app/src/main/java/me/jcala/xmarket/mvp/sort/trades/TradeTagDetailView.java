package me.jcala.xmarket.mvp.sort.trades;

import me.jcala.xmarket.view.RecyclerCommonAdapter;

public interface TradeTagDetailView {

    void whenLoadDataSuc(RecyclerCommonAdapter<?> adapter);//当数据获取成功

    void whenLoadDataFail(String errorMsg);//当数据获取失败

}
