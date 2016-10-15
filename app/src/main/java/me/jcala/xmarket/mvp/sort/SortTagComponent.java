package me.jcala.xmarket.mvp.sort;

import javax.inject.Singleton;

import dagger.Component;
import me.jcala.xmarket.mvp.user.login.LoginActivity;

@Singleton
@Component(modules = SortTagModule.class)
public interface SortTagComponent {
    void inject(LoginActivity activity);
}
