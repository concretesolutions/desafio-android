package br.com.githubrepos.pullrequests;


import android.webkit.URLUtil;

import androidx.annotation.NonNull;

import java.util.List;

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
    public void loadPullRequestList(final boolean doRefresh, String onwerLogin, String repositoryName) {
        if (doRefresh) {
            mView.setProgressIndicator(true);
        }

        mServiceApi.list(onwerLogin, repositoryName, new PullRequestServiceApi.PullRequestCallback<List<PullRequest>>() {
            @Override
            public void onLoaded(List<PullRequest> data) {
                if (doRefresh) {
                    mView.setProgressIndicator(false);
                }
                mView.showPullRequestList(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.showErrorMessage(errorMsg);
            }
        });

    }

    @Override
    public void openPullRequestDetails(PullRequest pullRequest) {
        String url = pullRequest.getHtmlUrl();
        if (null != url) {
            if (URLUtil.isValidUrl(url)) {
                mView.showPullRequestInBrowser(url);
            } else {
                mView.showErrorMessage("Cannot open ".concat(url).concat(" in browser"));
            }
        } else {
            mView.showErrorMessage("No url link available");
        }

    }
}
