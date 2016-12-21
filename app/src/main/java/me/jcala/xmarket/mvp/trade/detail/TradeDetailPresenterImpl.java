package me.jcala.xmarket.mvp.trade.detail;

import android.content.Context;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.User;
import me.jcala.xmarket.data.storage.UserIntermediate;
import me.jcala.xmarket.util.Interceptor;

public class TradeDetailPresenterImpl implements TradeDetailPresenter,TradeDetailModel.onDetailListener {
    private Context context;
    private TradeDetailView view;
    private TradeDetailModel model;
    private volatile String tradeId;
    private String tradeImg;
    private String team;

    public TradeDetailPresenterImpl(Context context, TradeDetailView view) {
        this.context = context;
        this.view = view;
        this.model=new TradeDetailModelImpl();
    }
    @Override
    public void loadData(String tradeId) {
        this.tradeId=tradeId;
        model.executeGetTradeReq(this,tradeId);
    }
    @Override
    public void loadTeamData() {
        view.whenShowProgress();
        String schoolName=UserIntermediate.instance.getUser(context).getSchool();
        model.executeGetTeamNamesReq(this,schoolName);
    }

    @Override
    public void donateTrade(String tradeId,String tradeImg,String team) {
        this.tradeId=tradeId;
        this.tradeImg=tradeImg;
        this.team=team;
        view.whenShowProgress();
        String userId=UserIntermediate.instance.getUser(context).getId();
        if (tradeImg==null||tradeImg.isEmpty()){
            return;
        }
        model.executeDonateReq(this,tradeId,tradeImg,userId,team);
    }

    @Override
    public void buyTrade(String tradeId) {
        this.tradeId=tradeId;
        view.whenShowProgress();
        User user=UserIntermediate.instance.getUser(context);
        model.executeBuyReq(this,user,tradeId);
    }

    @Override
    public void onGainDealComplete(Result<Trade> result) {
        view.whenHideProgress();
        int status= Interceptor.instance.tokenResultHandler(result,context);
        if (status==1){
            view.whenLoadTradeSuccess(result.getData());
        }else if (status==0){
            view.whenFail(result.getMsg());
        }else if (status==2){
           loadData(tradeId);
        }
    }
    @Override
    public void onGainTeamNamesComplete(Result<List<String>> result) {
        view.whenHideProgress();
        int status= Interceptor.instance.tokenResultHandler(result,context);
        if (status==1){
            view.whenLoadTeamSuccess(result.getData());
        }else if (status==0){
            view.whenFail(result.getMsg());
        }
    }

    @Override
    public void onDonateComplete(Result<String> result) {
        view.whenHideProgress();
        int status= Interceptor.instance.tokenResultHandler(result,context);
        if (status==1){
            view.whenDonateSuccess();
        }else if (status==0){
            view.whenFail(result.getMsg());
        }else if (status==2){
            donateTrade(tradeId,tradeImg,team);
        }
    }


    @Override
    public void onBuyComplete(Result<String> result) {
        view.whenHideProgress();
        int status= Interceptor.instance.tokenResultHandler(result,context);
        if (status==1){
            view.whenBuySuccess();
        }else if (status==0){
            view.whenFail(result.getMsg());
        }else if (status==2){
            buyTrade(tradeId);
        }
    }


}
