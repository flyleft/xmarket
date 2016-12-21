package me.jcala.xmarket.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import io.realm.Realm;
import me.jcala.xmarket.di.components.AppComponent;
import me.jcala.xmarket.di.components.DaggerAppComponent;
import me.jcala.xmarket.network.fresco.FrescoExecutor;
import me.jcala.xmarket.network.fresco.OkHttpImagePipelineConfigFactory;

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
        ImagePipelineConfig config= OkHttpImagePipelineConfigFactory
                .newBuilder(App.this,FrescoExecutor.instance.okHttpClient()).build();
        Fresco.initialize(this,config);
        Realm.init(this);
       /* RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        Realm.deleteRealm(realmConfig); // Delete Realm between app restarts.
        Realm.setDefaultConfiguration(realmConfig);*/
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
