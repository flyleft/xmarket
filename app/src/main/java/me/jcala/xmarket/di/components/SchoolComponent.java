package me.jcala.xmarket.di.components;

import javax.inject.Singleton;
import dagger.Component;
import me.jcala.xmarket.di.modules.SchoolModule;
import me.jcala.xmarket.mvp.school.SchoolFragment;

@Singleton
@Component(modules = SchoolModule.class)
public interface SchoolComponent {
    void inject(SchoolFragment schoolFragment);
}
