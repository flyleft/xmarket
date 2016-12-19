package me.jcala.xmarket.mvp.user.trade;

import me.jcala.xmarket.view.RecyclerCommonAdapter;

public interface UserTradeView {
    void whenLoadDataSuccess(RecyclerCommonAdapter<?> adapter);//获取数据成功，显示商品列表
    void whenStartProgress();//显示进度条
    void whenStopProgress();//隐藏进度条
}
