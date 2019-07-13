package com.concrete.android.challenge.ui.repository;

import com.concrete.android.challenge.data.remote.response.paging.GithubPaging;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public interface RepositoryNavigator {

  void onSuccess();

  void handleError(Throwable throwable);

  void setGithubPaging(GithubPaging pageArrayList);

  void setAllowLoadMore(boolean allowLoadMore);
}
