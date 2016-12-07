package me.jcala.xmarket.mvp.trade;

import android.content.Context;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;

public class TradeDetailPresenterImpl implements TradeDetailPresenter,TradeDetailModel.onDeailLisener {
    private Context context;
    private TradeDetailView view;
    private TradeDetailModel model;

    public TradeDetailPresenterImpl(Context context, TradeDetailView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeDetailModelImpl();
    }

    @Override
    public void onComplete(Result<Trade> result) {

    }

    @Override
    public void loadData(String tradeId) {

    }

}
