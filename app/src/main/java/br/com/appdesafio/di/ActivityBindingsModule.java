package br.com.appdesafio.di;



import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class ActivityBindingsModule {


/*    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity loginActivityInjector();

    @ContributesAndroidInjector(modules = {ListRepositoryActivityModule.class*//*, FragmentBindingsModule.class*//*})
    abstract MainActivity mainActivityInjector();*/


}
