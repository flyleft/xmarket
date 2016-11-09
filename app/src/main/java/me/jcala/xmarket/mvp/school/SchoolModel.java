package me.jcala.xmarket.mvp.school;

import java.util.List;

import me.jcala.xmarket.data.pojo.Trade;

public interface SchoolModel {
    interface onGainListener{
        void success(List<Trade> itemList);
        void fail();
    }
    void getSchoolDeals(int page,final onGainListener listener);
}
