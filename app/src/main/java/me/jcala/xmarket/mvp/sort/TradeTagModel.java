package me.jcala.xmarket.mvp.sort;

import java.util.List;

import io.realm.Realm;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.TradeTag;

interface TradeTagModel {

    interface onGainListener {
        void onComplete(Result<List<TradeTag>> result,Realm realmDefault);
    }

    void executeGetTagReq(final onGainListener listener, Realm realm);

}
