package me.jcala.xmarket.mvp.user.trades.sell;

import android.content.Context;

public class TradeSellPresenterImpl
        implements TradeSellPresenter,TradeSellModel.onTradeSellListener {
    private Context context;
    private TradeSellView view;
    private TradeSellModel model;

    public TradeSellPresenterImpl(Context context, TradeSellView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeSellModelImpl();
    }

}
