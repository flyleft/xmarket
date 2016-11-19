package me.jcala.xmarket.mvp.user.trades.sold;

import android.os.Bundle;

import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;

public class TradeSoldActivity extends BaseActivity implements TradeSoldView{
    @Override
    protected void initViews(Bundle savedInstanceState) {
      setContentView(R.layout.trade_sold_activity);
    }
}
