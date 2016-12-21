package me.jcala.xmarket.di.modules;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.jcala.xmarket.R;
import me.jcala.xmarket.di.qualifier.LoginProgress;
import me.jcala.xmarket.di.qualifier.RegisterProgress;
import me.jcala.xmarket.mvp.user.login.LoginRegisterPresenter;
import me.jcala.xmarket.mvp.user.login.LoginRegisterPresenterImpl;
import me.jcala.xmarket.mvp.user.login.LoginRegisterView;

@Module
public class LoginRegisterModule {
    private LoginRegisterView view;
    private Context context;

    public LoginRegisterModule(Context context,LoginRegisterView view) {
        this.view = view;
        this.context=context;
    }
    @Singleton @Provides LoginRegisterPresenter provideLoginRegisterPre(){
       return new LoginRegisterPresenterImpl(view,context);
    }

    @LoginProgress
    @Singleton @Provides MaterialDialog provideLoginProgress(){
        return new MaterialDialog.Builder(context)
                .content(R.string.login_dialog_content)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .title(R.string.dialog_wait)
                .build();
    }
    @RegisterProgress
    @Singleton @Provides MaterialDialog provideRegisterProgress(){
        return new MaterialDialog.Builder(context)
                .content(R.string.register_dialog_content)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .title(R.string.dialog_wait)
                .build();
    }
}
