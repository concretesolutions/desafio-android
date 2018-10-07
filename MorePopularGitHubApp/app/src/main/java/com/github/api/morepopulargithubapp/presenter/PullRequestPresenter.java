package com.github.api.morepopulargithubapp.presenter;

import com.github.api.morepopulargithubapp.model.vo.PullRequest;
import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.view.PullRequestView;

import java.util.List;

public interface PullRequestPresenter {

    void initPresenter(PullRequestView pullRequestView, Repository repository, List<PullRequest> pullRequests);

    void searchPullRequest();

    void onDestroy();

}
