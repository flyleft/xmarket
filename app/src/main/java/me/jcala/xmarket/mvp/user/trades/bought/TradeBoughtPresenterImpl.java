package me.jcala.xmarket.mvp.user.trades.bought;

import android.content.Context;

public class TradeBoughtPresenterImpl
        implements TradeBoughtPresenter,TradeBoughtModel.onTradeBoughtLisenter{

    private Context context;
    private TradeBoughtModel model;
    private TradeBoughtView view;

    public TradeBoughtPresenterImpl(Context context, TradeBoughtView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeBoughtModelImpl();
    }
}
