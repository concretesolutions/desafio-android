package br.com.githubrepos.pullrequests;

import java.util.List;

import br.com.githubrepos.data.entity.PullRequest;

public interface PullRequestsContract {

    interface View {
        void setProgressIndicator(boolean isActive);

        void showPullRequestList(List<PullRequest> pullRequestList);

        void showPullRequestInBrowser(String url);

        void showErrorMessage(String errorMessage);
    }

    interface UserActionsListener {

        void loadPullRequestList(boolean forceUpdate, String ownerLogin, String repositoryName);

        void openPullRequestDetails(PullRequest pullRequest);
    }
}
