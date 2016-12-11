package me.jcala.xmarket.mvp.trade;

import android.content.Context;

import com.orhanobut.logger.Logger;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.data.storage.UserIntermediate;

public class TradeDetailPresenterImpl implements TradeDetailPresenter,TradeDetailModel.onDetailListener {
    private Context context;
    private TradeDetailView view;
    private TradeDetailModel model;

    public TradeDetailPresenterImpl(Context context, TradeDetailView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeDetailModelImpl();
    }

    @Override
    public void onGainDealComplete(Result<Trade> result) {
        if (result==null){
            return;
        }

        switch (result.getCode()) {
            case 100:
                view.whenSuccess(result.getData());
                break;
            case 99:
                view.whenFail(result.getMsg());
                break;
            default:
        }
    }

    @Override
    public void buyTrade(String tradeId) {
        User user=UserIntermediate.instance.getUser(context);
        model.executeBuyReq(this,user,tradeId);
    }

    @Override
    public void onBuyComplete(Result<Message> result) {
        if (result==null){
            return;
        }

        switch (result.getCode()) {
            case 100:
                view.whenBuySuccess();
                break;
            case 99:
                view.whenFail(result.getMsg());
                break;
            default:
        }
    }

    @Override
    public void loadData(String tradeId) {
        model.executeDetailReq(this,tradeId);
    }

}
