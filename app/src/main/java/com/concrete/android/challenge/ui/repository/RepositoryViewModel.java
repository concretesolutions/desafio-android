package com.concrete.android.challenge.ui.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.concrete.android.challenge.data.model.Repository;
import com.concrete.android.challenge.data.remote.GithubService;
import com.concrete.android.challenge.ui.base.BaseViewModel;
import com.concrete.android.challenge.utils.rx.SchedulerProvider;
import java.util.List;
import timber.log.Timber;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class RepositoryViewModel extends BaseViewModel<RepositoryNavigator> {

  private final MutableLiveData<List<Repository>> repositoryListLiveData;

  public RepositoryViewModel(GithubService service,
      SchedulerProvider schedulerProvider) {
    super(service, schedulerProvider);
    repositoryListLiveData = new MutableLiveData<>();
    getRepositories("language:Java", "stars", 1);
  }

  public void getRepositories(String query, String sort, int page) {
    setIsLoading(true);
    getCompositeDisposable().add(
        getService().getRepositories(query, sort, page)
            .compose(throwOnHttpException())
            .map(response -> {
              getNavigator().setGithubPaging(findPaging(response.headers()));
              return response;
            })
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(response -> {
              if (response.body() != null
                  && response.body().getItems() != null) {
                repositoryListLiveData.setValue(response.body().getItems());
              }
              setIsLoading(false);
              getNavigator().onSuccess();
              getNavigator().setAllowLoadMore(true);
            }, throwable -> {
              setIsLoading(false);
              Timber.e(throwable);
              getNavigator().handleError(throwable);
              getNavigator().setAllowLoadMore(true);
            }));
  }

  public void refreshRepositoriesList() {
    getRepositories("language:Java", "stars", 1);
  }

  public LiveData<List<Repository>> getRepositoryListLiveData() {
    return repositoryListLiveData;
  }
}
