package me.jcala.xmarket.mvp.school;

import me.jcala.xmarket.AppConf;
import me.jcala.xmarket.mock.TradeMock;

class SchoolModelImpl implements SchoolModel{

    @Override
    public void getSchoolDeals(int page,final onGainListener listener) {
        if (AppConf.useMock){
            listener.onComplete(new TradeMock().gainSchoolTrades());
            return;
        }

    }

}
