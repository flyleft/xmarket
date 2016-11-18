package me.jcala.xmarket.mvp.user.trades.add;

import android.os.Bundle;

import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;

public class TradeAddActivity extends BaseActivity implements TradeAddView{

    protected TradeAddPresenter presenter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.trade_add_activity);
        initData();
    }

    private void initData(){

    }

}
