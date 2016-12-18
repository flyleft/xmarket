package me.jcala.xmarket.mvp.message;

import io.realm.Realm;
import me.jcala.xmarket.data.pojo.Message;

public interface MessagePresenter {

    void initView(Realm realm);

    void confirmDeal(Message item);//确认交易

}
