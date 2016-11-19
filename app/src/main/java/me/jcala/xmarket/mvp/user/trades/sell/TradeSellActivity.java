package me.jcala.xmarket.mvp.user.trades.sell;

import android.os.Bundle;

import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;

public class TradeSellActivity extends BaseActivity implements TradeSellView{

    @Override
    protected void initViews(Bundle savedInstanceState) {
       setContentView(R.layout.trade_sell_activity);
    }

}
