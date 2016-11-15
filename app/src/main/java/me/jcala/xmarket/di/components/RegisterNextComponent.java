package me.jcala.xmarket.di.components;

import javax.inject.Singleton;

import dagger.Component;
import me.jcala.xmarket.di.modules.RegisterNextModule;
import me.jcala.xmarket.mvp.user.login.register.next.RegisterNextActivity;

@Singleton
@Component(modules = RegisterNextModule.class)
public interface RegisterNextComponent {
    void inject(RegisterNextActivity activity);
}
