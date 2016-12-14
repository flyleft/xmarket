package me.jcala.xmarket.mvp.message;

import me.jcala.xmarket.data.pojo.Message;

public interface MessagePresenter {

    void updateMessageList();

    void confirmDeal(Message item);//确认交易

}
