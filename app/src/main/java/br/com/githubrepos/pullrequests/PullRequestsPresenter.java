package br.com.githubrepos.pullrequests;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import br.com.githubrepos.data.entity.PullRequest;
import br.com.githubrepos.data.service.PullRequestServiceApi;

public class PullRequestsPresenter implements PullRequestsContract.UserActionsListener {

    private PullRequestServiceApi mServiceApi;
    private PullRequestsContract.View mView;

    public PullRequestsPresenter(@NonNull PullRequestServiceApi pullRequestServiceApi,
                                 @NonNull PullRequestsContract.View pullRequestsView) {
        this.mServiceApi = Preconditions.checkNotNull(pullRequestServiceApi,
                "mServiceApi cannot be null!");
        this.mView = Preconditions.checkNotNull(pullRequestsView,
                "mView cannot be null!");
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
