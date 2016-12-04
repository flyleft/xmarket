package me.jcala.xmarket.mvp.message;

public interface MessagePresenter {

    void gainMessages();

    void confirmDeal(String userId,String tradeId,String id);//确认交易

}
