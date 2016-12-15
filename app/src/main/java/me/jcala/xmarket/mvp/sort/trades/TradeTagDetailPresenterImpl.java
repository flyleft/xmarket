package me.jcala.xmarket.mvp.sort.trades;


import android.content.Context;

public class TradeTagDetailPresenterImpl implements TradeTagDetailPresenter{
    private Context context;
    private TradeTagDetailView view;
    private TradeTagDetailModel model;

    public TradeTagDetailPresenterImpl(Context context, TradeTagDetailView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeTagDetailModelImpl();
    }

    @Override
    public void getTradeListByTag(String tagName) {

    }
}
