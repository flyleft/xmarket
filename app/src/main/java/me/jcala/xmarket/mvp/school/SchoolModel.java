package me.jcala.xmarket.mvp.school;

public interface SchoolModel {
    interface onGainListener{
        void success();
        void fail();
    }
    void getSchoolDeals(int page,final onGainListener listener);
}
