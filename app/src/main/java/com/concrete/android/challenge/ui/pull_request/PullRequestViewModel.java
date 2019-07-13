package com.concrete.android.challenge.ui.pull_request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.concrete.android.challenge.data.model.PullRequest;
import com.concrete.android.challenge.data.remote.GithubService;
import com.concrete.android.challenge.ui.base.BaseViewModel;
import com.concrete.android.challenge.utils.rx.SchedulerProvider;
import java.util.List;
import timber.log.Timber;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public class PullRequestViewModel extends BaseViewModel<PullRequestNavigator> {

  private final MutableLiveData<List<PullRequest>> pullRequestListLiveData;

  public PullRequestViewModel(GithubService service,
      SchedulerProvider schedulerProvider) {
    super(service, schedulerProvider);
    pullRequestListLiveData = new MutableLiveData<>();
  }

  public void getPullRequests(String userName, String projectName, String state, int page) {
    setIsLoading(true);
    getCompositeDisposable().add(
        getService().getPullRequests(userName, projectName, state, page)
            .compose(throwOnHttpException())
            .map(response -> {
              getNavigator().setGithubPaging(findPaging(response.headers()));
              return response;
            })
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(response -> {
              if (response.body() != null) {
                pullRequestListLiveData.setValue(response.body());
                getNavigator().updateTotalOpenedAndClosed(response.body());
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

  public LiveData<List<PullRequest>> getPullRequestListLiveData() {
    return pullRequestListLiveData;
  }
}
