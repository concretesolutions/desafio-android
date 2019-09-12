package com.feliperamoscarvalho.appconsultagithub.pullrequests;

import android.support.annotation.NonNull;

import com.feliperamoscarvalho.appconsultagithub.data.PullRequestServiceAPI;
import com.feliperamoscarvalho.appconsultagithub.data.model.Pull;

import java.util.List;

/**
 * Presenter of the PullRequests view (PullRequestsFragment). Implements PullRequestsContract
 * interface user actions
 */
public class PullRequestsPresenter implements PullRequestsContract.UserActionsListener {

    private PullRequestServiceAPI mApi;
    private final PullRequestsContract.View mPullRequestsView;

    /**
     * Constructor gets model (PullRequestServiceAPI) and view (PullRequestsFragment.View)
     */
    public PullRequestsPresenter(PullRequestServiceAPI api, PullRequestsContract.View view) {
        mApi = api;
        mPullRequestsView = view;
    }

    /**
     * Method that loads pull requests data into the view, using the model function to fetch data
     *
     * @param login          login that will be used in the pull requests search.
     * @param repositoryName name of the repository that will be used in the pull requests search.
     */
    @Override
    public void loadPullRequest(String login, String repositoryName) {

        mPullRequestsView.setLoading(true);
        mApi.getPullRequests(login, repositoryName,
                new PullRequestServiceAPI.PullRequestServiceCallback<List<Pull>>() {
            @Override
            public void onLoaded(List<Pull> pullRequests) {
                mPullRequestsView.setLoading(false);
                mPullRequestsView.showPullRequestsItems(pullRequests);
            }
        });

    }

    /**
     * Method that calls the view function to open the browser with pull request data.
     *
     * @param htmlUrl address that will open in the browser
     */
    @Override
    public void openDetailsInBrowser(@NonNull String htmlUrl) {
        mPullRequestsView.showDetailsInBrowser(htmlUrl);
    }
}
