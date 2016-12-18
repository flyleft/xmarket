package me.jcala.xmarket.mvp.message;

import me.jcala.xmarket.data.pojo.Message;

public interface MessagePresenter {

    void initView();

    void confirmDeal(Message item);//确认交易

    void refreshView();

}
