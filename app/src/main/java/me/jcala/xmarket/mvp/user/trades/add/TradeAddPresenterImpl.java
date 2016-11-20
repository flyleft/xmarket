package me.jcala.xmarket.mvp.user.trades.add;

import android.content.Context;

import me.jcala.xmarket.data.dto.Result;

public class TradeAddPresenterImpl
        implements TradeAddPresenter,TradeAddModel.onTradeAddListener{
    private TradeAddModel model;
    private TradeAddView view;
    private Context context;

    public TradeAddPresenterImpl(Context context, TradeAddView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeAddModelImpl();
    }

    @Override
    public void hasGotAddTradeResult(Result<?> result) {

    }

    @Override
    public void tradeAdd() {

    }
}
