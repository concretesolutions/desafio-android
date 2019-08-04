package br.com.githubrepos.pullrequests;


import androidx.annotation.NonNull;

import br.com.githubrepos.data.entity.PullRequest;
import br.com.githubrepos.data.service.PullRequestServiceApi;

public class PullRequestsPresenter implements PullRequestsContract.UserActionsListener {

    private PullRequestServiceApi mServiceApi;
    private PullRequestsContract.View mView;

    public PullRequestsPresenter(@NonNull PullRequestServiceApi pullRequestServiceApi,
                                 @NonNull PullRequestsContract.View pullRequestsView) {
        this.mServiceApi = pullRequestServiceApi;
        this.mView = pullRequestsView;
    }

    @Override
    public void loadPullRequestList(boolean forceUpdate) {

    }

    @Override
    public void selectPullRequest() {

    }

    @Override
    public void unselectPullRequest() {

    }

    @Override
    public void deleteSelectedPullRequest() {

    }

    @Override
    public void openPullRequestDetails(PullRequest pullRequest) {

    }
}
