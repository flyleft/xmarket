package me.jcala.xmarket.mvp.a_base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public abstract  class BaseActivity extends AppCompatActivity {

    protected String TAG = "BaseActivity";


    protected abstract void initViews(Bundle savedInstanceState);

    protected  void initVariables(){}

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        initViews(savedInstanceState);
        initVariables();
    }


}
