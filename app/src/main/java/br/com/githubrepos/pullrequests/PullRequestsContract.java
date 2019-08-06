package br.com.githubrepos.pullrequests;

import java.util.List;

import br.com.githubrepos.data.entity.PullRequest;

public interface PullRequestsContract {

    interface View {
        void setProgressIndicator(boolean isActive);

        void showPullRequestList(List<PullRequest> pullRequestList);

        void showPullRequestInBrowser();
    }

    interface UserActionsListener {

        void loadPullRequestList(boolean forceUpdate);

        void selectPullRequest();

        void unselectPullRequest();

        void deleteSelectedPullRequest();

        void openPullRequestDetails(PullRequest pullRequest);
    }
}
