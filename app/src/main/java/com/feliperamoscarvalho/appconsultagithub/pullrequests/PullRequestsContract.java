package com.feliperamoscarvalho.appconsultagithub.pullrequests;

import android.support.annotation.NonNull;

import com.feliperamoscarvalho.appconsultagithub.data.model.Pull;

import java.util.List;

/**
 * Contract that defines how the pull requests screen should work.
 * View actions should be implemented by the view and user actions
 * should be implemented by the presenter.
 */
public interface PullRequestsContract {

    interface View {

        void setLoading(boolean isActive);

        void showPullRequestsItems(List<Pull> pullRequests);

        void showDetailsInBrowser(String htmlUrl);
    }

    interface UserActionsListener {

        void loadPullRequest(String login, String repositoryName);

        void openDetailsInBrowser(@NonNull String htmlUrl);
    }
}
