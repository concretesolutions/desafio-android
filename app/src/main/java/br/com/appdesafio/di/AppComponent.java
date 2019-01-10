package br.com.appdesafio.di;


import android.app.Application;

import javax.inject.Singleton;

import br.com.appdesafio.application.App;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingsModule.class,

        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App>  {


    @Component.Builder
    interface Buider {

        @BindsInstance
        Buider application(Application application);

        AppComponent build();
    }
}
