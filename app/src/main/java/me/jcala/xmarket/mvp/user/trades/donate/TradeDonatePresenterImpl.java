package me.jcala.xmarket.mvp.user.trades.donate;

import android.content.Context;

public class TradeDonatePresenterImpl
        implements TradeDonatePresenter,TradeDonateModel.onTradeDonateLisener{
    private Context context;
    private TradeDonateModel model;
    private TradeDonateView view;

    public TradeDonatePresenterImpl(Context context, TradeDonateView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeDonateModelImpl();
    }
}
