package me.jcala.xmarket.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/9/1.
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
    }

    public static Context getContext() {
        return context;
    }

}
