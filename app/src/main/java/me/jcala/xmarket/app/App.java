package me.jcala.xmarket.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.jcala.xmarket.di.components.AppComponent;
import me.jcala.xmarket.di.components.DaggerAppComponent;

public class App extends Application {
    private static final String TAG="App";
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
        initialize();
    }
    private void initialize(){
        Fresco.initialize(this);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
