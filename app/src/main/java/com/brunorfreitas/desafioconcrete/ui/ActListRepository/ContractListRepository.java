package com.brunorfreitas.desafioconcrete.ui.ActListRepository;

import android.view.View;

import com.brunorfreitas.desafioconcrete.data.Model.Item;

import java.util.List;

public interface ContractListRepository {

    interface View{
        void showProgressBar();
        void hideProgressBar();
        void showRepoList(List<Item> repoList);
        void showError(String msg);
        void openListPullRequestRepo(String owner, String repo);

        void addListRepo(List<Item> items);
    }

    interface Presenter{
        void loadListRepo(int page);
    }
}
