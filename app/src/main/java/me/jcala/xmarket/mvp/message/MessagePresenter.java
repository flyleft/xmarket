package me.jcala.xmarket.mvp.message;

public interface MessagePresenter {

    void gainMessages();

    void confirmDeal(String userId,String tradeId);//确认交易

}
