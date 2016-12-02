package me.jcala.xmarket.mvp.school;

import java.util.List;

import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Trade;

public interface SchoolModel {
    interface onGainListener{
        void onComplete(Result<List<Trade>> result);
        void onFail();
    }
    void getSchoolDeals(int page,final onGainListener listener);
}
