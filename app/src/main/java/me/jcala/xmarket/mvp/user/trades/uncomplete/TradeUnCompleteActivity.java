package me.jcala.xmarket.mvp.user.trades.uncomplete;

import android.os.Bundle;

import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;

public class TradeUnCompleteActivity extends BaseActivity implements TradeUnCompleteView{
    @Override
    protected void initViews(Bundle savedInstanceState) {
      setContentView(R.layout.trade_uncomplete_activity);
    }
}
