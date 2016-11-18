package me.jcala.xmarket.mvp.user.trades.add;

import android.os.Bundle;

import com.jph.takephoto.app.TakePhotoActivity;

import me.jcala.xmarket.R;

public class TradeAddActivity extends TakePhotoActivity implements TradeAddView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade_add_activity);
    }

}
