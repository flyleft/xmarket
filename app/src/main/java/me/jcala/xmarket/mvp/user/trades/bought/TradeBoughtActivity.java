package me.jcala.xmarket.mvp.user.trades.bought;

import android.os.Bundle;

import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;

public class TradeBoughtActivity extends BaseActivity implements TradeBoughtView {
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.trade_bought_activity);

    }
}
