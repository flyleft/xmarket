package me.jcala.xmarket.di.components;

import javax.inject.Singleton;

import dagger.Component;
import me.jcala.xmarket.app.App;
import me.jcala.xmarket.di.modules.ApplicationModule;

@Singleton
@Component(modules = {
        ApplicationModule.class
})
public interface AppComponent {
    App inject(App app);
}
