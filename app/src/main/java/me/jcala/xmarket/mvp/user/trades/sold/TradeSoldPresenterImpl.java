package me.jcala.xmarket.mvp.user.trades.sold;

import android.content.Context;

public class TradeSoldPresenterImpl
        implements TradeSoldPresenter,TradeSoldModel.onTradeSoldListener {
    private Context context;
    private TradeSoldView view;
    private TradeSoldModel model;

    public TradeSoldPresenterImpl(Context context, TradeSoldView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeSoldModelImpl();
    }
}
