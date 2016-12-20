package me.jcala.xmarket.mvp.trade.detail;

public interface TradeDetailPresenter {

    void loadData(String tradeId);

    void buyTrade(String tradeId);

    void loadTeamData();//获取志愿队名称列表

    void donateTrade(String tradeId,String tradeImg,String team);
}
