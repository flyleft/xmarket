package me.jcala.xmarket.mvp.sort;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
    public SortTagPre providePre(){
        return new SortTagPreImpl(context,view);
    }
}
