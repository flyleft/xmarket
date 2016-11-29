package me.jcala.xmarket.mvp.test;

import android.os.Bundle;

import butterknife.ButterKnife;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseActivity;

public class TestActivity extends BaseActivity{


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.test_activity);
        ButterKnife.bind(this);
    }



}
