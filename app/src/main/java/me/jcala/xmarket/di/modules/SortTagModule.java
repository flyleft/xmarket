package me.jcala.xmarket.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.jcala.xmarket.mvp.sort.TradeTagPresenter;
import me.jcala.xmarket.mvp.sort.TradeTagPresenterImpl;
import me.jcala.xmarket.mvp.sort.TradeTagView;

@Module
public class SortTagModule {
    private Context context;
    private TradeTagView view;

    public SortTagModule(Context context, TradeTagView view) {
        this.context = context;
        this.view = view;
    }

    @Singleton @Provides TradeTagPresenter providePresenter(){
        return new TradeTagPresenterImpl(context,view);
    }
}
