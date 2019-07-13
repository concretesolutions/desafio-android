package com.concrete.android.challenge.ui.pull_request;

import com.concrete.android.challenge.data.model.PullRequest;
import com.concrete.android.challenge.data.remote.response.paging.GithubPaging;
import java.util.List;

/**
 * @author Thiago Corredo
 * @since 2019-07-12
 */
public interface PullRequestNavigator {

  void onSuccess();

  void handleError(Throwable throwable);

  void setGithubPaging(GithubPaging pageArrayList);

  void updateTotalOpenedAndClosed(List<PullRequest> pullRequests);

  void setAllowLoadMore(boolean allowLoadMore);
}
