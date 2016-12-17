package me.jcala.xmarket.mvp.school;

import java.util.List;

import io.realm.Realm;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;

public interface SchoolModel {

    interface onGainListener{
        void onReqComplete(Result<List<Trade>> result, Realm realm);
    }
    void executeGetTradesReq(onGainListener listener,String schoolName,int page,Realm realm);
}
