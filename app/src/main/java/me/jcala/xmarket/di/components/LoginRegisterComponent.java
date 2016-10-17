package me.jcala.xmarket.di.components;

import javax.inject.Singleton;

import dagger.Component;
import me.jcala.xmarket.di.modules.LoginRegisterModule;
import me.jcala.xmarket.mvp.user.login.LoginRegisterActivity;

@Singleton
@Component(modules = LoginRegisterModule.class)
public interface LoginRegisterComponent {
    void inject(LoginRegisterActivity activity);
}
