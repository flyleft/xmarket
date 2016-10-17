package me.jcala.xmarket.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.jcala.xmarket.mvp.user.login.LoginRegisterPresenter;
import me.jcala.xmarket.mvp.user.login.LoginRegisterPresenterImpl;
import me.jcala.xmarket.mvp.user.login.LoginRegisterView;

@Module
public class LoginRegisterModule {
    private LoginRegisterView view;

    public LoginRegisterModule(LoginRegisterView view) {
        this.view = view;
    }

    @Singleton @Provides LoginRegisterPresenter provideLoginRegisterPre(){
       return new LoginRegisterPresenterImpl(view);
    }
}
