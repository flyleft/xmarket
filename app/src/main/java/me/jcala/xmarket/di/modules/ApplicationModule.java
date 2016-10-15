package me.jcala.xmarket.di.modules;

import android.content.Context;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import me.jcala.xmarket.app.App;

@Module
public class ApplicationModule {
    public static final String TAG = "AppModule";
    private final App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }
}
