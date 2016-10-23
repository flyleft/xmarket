package me.jcala.xmarket.mvp.school;

import java.util.List;

import me.jcala.xmarket.data.dao.DealItem;

public interface SchoolModel {
    interface onGainListener{
        void success(List<DealItem> itemList);
        void fail();
    }
    void getSchoolDeals(int page,final onGainListener listener);
}
