package me.jcala.xmarket.mvp.trade.detail;

import android.content.Context;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
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
    public void loadData(String tradeId) {
        model.executeGetTradeReq(this,tradeId);
    }
    @Override
    public void loadTeamData() {
        view.whenShowProgress();
        String schoolName=UserIntermediate.instance.getUser(context).getSchool();
        model.executeGetTeamNamesReq(this,schoolName);
    }

    @Override
    public void donateTrade(String tradeId,String team) {
        view.whenShowProgress();
        String userId=UserIntermediate.instance.getUser(context).getId();
        model.executeDonateReq(this,tradeId,userId,team);
    }

    @Override
    public void buyTrade(String tradeId) {
        view.whenShowProgress();
        User user=UserIntermediate.instance.getUser(context);
        model.executeBuyReq(this,user,tradeId);
    }

    @Override
    public void onGainDealComplete(Result<Trade> result) {
        if (resultHandler(result)){
            view.whenLoadTradeSuccess(result.getData());
        }else {
            view.whenFail(result.getMsg());
        }
    }
    @Override
    public void onGainTeamNamesComplete(Result<List<String>> result) {
        if (resultHandler(result)){
              view.whenLoadTeamSuccess(result.getData());
        }else {
            view.whenFail(result.getMsg());
        }
    }

    @Override
    public void onDonateComplete(Result<String> result) {
        if (resultHandler(result)){
            view.whenDonateSuccess();
        }else {
            view.whenFail(result.getMsg());
        }
    }


    @Override
    public void onBuyComplete(Result<String> result) {
        if (resultHandler(result)){
            view.whenBuySuccess();
        }
        else {
            view.whenFail(result.getMsg());
        }
    }

    private boolean resultHandler(Result<?> result){
        view.whenHideProgress();
        if (result==null){
            return false;
        }

        switch (result.getCode()) {
            case 100:
                return true;
            case 99:
                return false;
            default:
                return false;
        }
    }

}
