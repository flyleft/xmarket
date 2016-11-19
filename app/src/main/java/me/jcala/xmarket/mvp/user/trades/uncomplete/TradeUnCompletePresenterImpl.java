package me.jcala.xmarket.mvp.user.trades.uncomplete;


import android.content.Context;

public class TradeUnCompletePresenterImpl
        implements TradeUnCompletePresenter,TradeUnCompleteModel.TradeUnCompleteListener {
    private Context context;
    private TradeUnCompleteView view;
    private TradeUnCompleteModel model;

    public TradeUnCompletePresenterImpl(Context context, TradeUnCompleteView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeUnCompleteModelImpl();
    }
}
