package me.jcala.xmarket.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.jcala.xmarket.mvp.sort.SortTagPresenter;
import me.jcala.xmarket.mvp.sort.SortTagPresenterImpl;
import me.jcala.xmarket.mvp.sort.SortTagView;

@Module
public class SortTagModule {
    private Context context;
    private SortTagView view;

    public SortTagModule(Context context, SortTagView view) {
        this.context = context;
        this.view = view;
    }

    @Singleton
    @Provides
    public SortTagPresenter providePre(){
        return new SortTagPresenterImpl(context,view);
    }
}
