package me.jcala.xmarket.ui.activity.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/9/29.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public static final String TAG = "BaseActivity";
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        initView(savedInstanceState);
        initData();
    }


    public abstract void initView( Bundle savedInstanceState);
    public abstract void initData();
}
