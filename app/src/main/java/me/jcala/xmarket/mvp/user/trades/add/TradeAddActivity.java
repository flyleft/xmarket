package me.jcala.xmarket.mvp.user.trades.add;

import android.os.Bundle;

import com.jph.takephoto.app.TakePhotoActivity;

import me.jcala.xmarket.R;

public class TradeAddActivity extends TakePhotoActivity implements TradeAddView{

    TradeAddPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade_add_activity);
        initData();
    }
    private void initData(){
        presenter=new TradeAddPresenterImpl(this,this);
        
    }

    @Override
    public void whenAddSuccess() {

    }

    @Override
    public void whenFail(String errorMsg) {

    }
}
