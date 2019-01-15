package br.com.appdesafio.di;
import android.app.Application;
import br.com.appdesafio.repository.ListRepository;
import br.com.appdesafio.service.IService;
import br.com.appdesafio.viewmodel.ListPullRequestViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class ListPullRequestActivityModule {

    @Provides
    static ListPullRequestViewModel providePullRequestViewModel(final Application application,
                                                                final IService iService

    ) {
        return new ListPullRequestViewModel(new ListRepository(iService, application), application);
    }
}
