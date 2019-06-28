package com.brunorfreitas.desafioconcrete.ui.ActListPullRequestRepo;

import com.brunorfreitas.desafioconcrete.data.Model.Item;
import com.brunorfreitas.desafioconcrete.data.Model.PullRequestRepositoryResponse;

import java.util.List;

public interface ContractListPullRequestRepo {

    interface View{
        void showProgressBar();
        void hideProgressBar();
        void showPullRequestRepoList(List<PullRequestRepositoryResponse> repoList);
        void showError(String msg);
    }

    interface Presenter{
        void loadListPullRequestRepo(String owner, String repo);
    }
}
