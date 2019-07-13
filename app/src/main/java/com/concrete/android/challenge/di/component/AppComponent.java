package com.concrete.android.challenge.di.component;

import android.app.Application;
import com.concrete.android.challenge.MyAppAplication;
import com.concrete.android.challenge.di.module.ActivityModule;
import com.concrete.android.challenge.di.module.AppModule;
import com.concrete.android.challenge.di.module.FragmentModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
@Singleton
@Component(modules = {
    AndroidInjectionModule.class, AppModule.class, ActivityModule.class, FragmentModule.class
})
public interface AppComponent {

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }

  void inject(MyAppAplication application);
}
