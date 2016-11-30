package me.jcala.xmarket.mvp.user.trades.add;

import java.util.List;

import me.jcala.xmarket.data.pojo.TradeTag;

public interface TradeAddView {

    void whenAddSuccess();//发布商品成功

    void whenGetTagListSuccess(List<TradeTag> tagList);//获取商品分类列表成功

    void whenFail(String errorMsg);//发生异常

    void whenStartProgress();//显示进度条

    void whenStopProgress();//隐藏进度条

}
