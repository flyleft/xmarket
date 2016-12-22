package me.jcala.xmarket.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import io.realm.Realm;
import me.jcala.xmarket.di.components.AppComponent;
import me.jcala.xmarket.di.components.DaggerAppComponent;
import me.jcala.xmarket.network.fresco.FrescoExecutor;
import me.jcala.xmarket.network.fresco.OkHttpNetworkFetcher;

public class App extends Application {
    private static final String TAG="App";
    private AppComponent appComponent;
    private static App instance;
    public static App getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.create();
        initialize();
    }
    private void initialize(){

        ImagePipelineConfig config=ImagePipelineConfig.newBuilder(App.this)
                .setNetworkFetcher(new OkHttpNetworkFetcher(FrescoExecutor.instance.okHttpClient())).build();
        Fresco.initialize(this,config);
        Realm.init(this);
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
