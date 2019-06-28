package com.brunorfreitas.desafioconcrete.ui.ActListPullRequestRepo;

import com.brunorfreitas.desafioconcrete.data.API.ApiGit;
import com.brunorfreitas.desafioconcrete.data.API.ApiGitImpl;
import com.brunorfreitas.desafioconcrete.data.Model.PullRequestRepositoryResponse;

import java.util.List;

public class PresenterListPullRequestRepo implements ContractListPullRequestRepo.Presenter {

    ContractListPullRequestRepo.View view;
    ApiGit apiGit;

    public PresenterListPullRequestRepo(ContractListPullRequestRepo.View view) {
        this.view = view;
        apiGit = new ApiGitImpl();
    }

    @Override
    public void loadListPullRequestRepo(String owner, String repo) {

        view.showProgressBar();

        apiGit.getPullRequestsRepository(owner, repo, new ApiGit.ApiServiceCallback<List<PullRequestRepositoryResponse>>() {
            @Override
            public void onSucces(List<PullRequestRepositoryResponse> result) {
                view.showPullRequestRepoList(result);
                view.hideProgressBar();
            }

            @Override
            public void onFailure(String message) {
                view.showError(message);
                view.hideProgressBar();
            }
        });

    }
}
