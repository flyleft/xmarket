package me.jcala.xmarket.mvp.trade;

import android.os.Bundle;

import butterknife.ButterKnife;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;

public class TradeDetailActivity extends BaseActivity {

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.trade_add_activity);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){

    }
}
