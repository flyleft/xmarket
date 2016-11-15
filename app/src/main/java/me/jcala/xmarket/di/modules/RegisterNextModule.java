package me.jcala.xmarket.di.modules;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.user.login.register.next.RegisterNextPresenter;
import me.jcala.xmarket.mvp.user.login.register.next.RegisterNextPresenterImpl;
import me.jcala.xmarket.mvp.user.login.register.next.RegisterNextView;

@Module
public class RegisterNextModule {
    private RegisterNextView view;
    private Context context;

    public RegisterNextModule(Context context, RegisterNextView view) {
        this.context = context;
        this.view = view;
    }

    @Singleton
    @Provides
    RegisterNextPresenter provideRegisterNextPresenter(){
        return new RegisterNextPresenterImpl(context,view);
    }

    @Singleton @Provides
    MaterialDialog provideRegisterNextProgress(){
        return new MaterialDialog.Builder(context)
                .content(R.string.register_dialog_content)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .title(R.string.dialog_wait)
                .build();
    }
}
