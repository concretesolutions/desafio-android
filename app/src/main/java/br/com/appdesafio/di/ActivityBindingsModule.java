package br.com.appdesafio.di;



import br.com.appdesafio.view.activity.ListPullRequestActivity;
import br.com.appdesafio.view.activity.ListRepositoryActivity;
import br.com.appdesafio.viewmodel.ListPullRequestViewModel;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class ActivityBindingsModule {

    @ContributesAndroidInjector(modules = ListRepositoryActivityModule.class)
    abstract ListRepositoryActivity listRepositoryActivityInjector();

   @ContributesAndroidInjector(modules = ListPullRequestActivityModule.class)
    abstract ListPullRequestActivity listPullRequestActivityInjector();


/*    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity loginActivityInjector();

    @ContributesAndroidInjector(modules = {ListRepositoryActivityModule.class*//*, FragmentBindingsModule.class*//*})
    abstract MainActivity mainActivityInjector();*/


}
