package me.jcala.xmarket.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.jcala.xmarket.mvp.school.SchoolPresenter;
import me.jcala.xmarket.mvp.school.SchoolPresenterImpl;
import me.jcala.xmarket.mvp.school.SchoolView;

@Module
public class SchoolModule {
    private SchoolView view;
    private Context context;
    public SchoolModule(Context context, SchoolView view) {
        this.context = context;
        this.view = view;
    }

    @Singleton
    @Provides
    SchoolPresenter providePresenter(){
        return new SchoolPresenterImpl(context,view);
    }
}
