package br.com.appdesafio.di;

import android.app.Application;

import br.com.appdesafio.model.persistence.SharedPreference;
import br.com.appdesafio.repository.ListRepository;
import br.com.appdesafio.service.IService;
import br.com.appdesafio.viewmodel.LisRepositoryViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class ListRepositoryActivityModule {

    @Provides
    static LisRepositoryViewModel provideRepositoryViewModel(final Application application, final IService iService, final SharedPreference sharedPreference) {//*, final AppExecutors appExecutors, final AppDatabase appDatabase, final SharedPreferencesUtil sharedPreferencesUtil*//*) {
            return new LisRepositoryViewModel(new ListRepository(iService, sharedPreference, application ), application);
}
}


 /*   @Provides
    static MainViewModel provideMainViewModel(final Application application, final IService iService, final SharedPreference sharedPreference*//*, final AppExecutors appExecutors, final AppDatabase appDatabase, final SharedPreferencesUtil sharedPreferencesUtil*//*) {
        return new MainViewModel(application, sharedPreference);
    }*/

