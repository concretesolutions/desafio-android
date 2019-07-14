package com.felipe.palma.desafioconcrete.ui.pullrequest;

import com.felipe.palma.desafioconcrete.domain.response.PullRequestResponse;
import com.felipe.palma.desafioconcrete.network.IServiceGithub;
import com.felipe.palma.desafioconcrete.network.ServiceGithubImp;

import java.util.List;

/**
 * Created by Felipe Palma on 13/07/2019.
 */
public class PullRequestPresenter implements PullRequestContract.Presenter {

    private PullRequestContract.View view;

    public PullRequestPresenter(PullRequestContract.View view) {
        this.view = view;
    }

    @Override
    public void loadPullRequests(String owner, String repo) {
        this.view.showDialog();
        ServiceGithubImp mServiceGithubImp = new ServiceGithubImp();
        mServiceGithubImp.getPullRequests(owner, repo, new IServiceGithub.IServiceCallback<List<PullRequestResponse>>() {
            @Override
            public void onSuccess(List<PullRequestResponse> pullRequestResponse) {
                view.showPullRequests(pullRequestResponse);
                view.hideDialog();
            }

            @Override
            public void onError(String error) {
                view.showError(error);
                view.hideDialog();
            }
        });
    }
}
