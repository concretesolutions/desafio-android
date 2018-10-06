package com.github.api.morepopulargithubapp.presenter;

import android.support.v7.widget.LinearLayoutManager;

import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.view.RepositoryView;

import java.util.List;

public interface RepositoryPresenter {

    void initPresenter(boolean isChangeOrientation, boolean isScrolling, boolean isLastPage, boolean isGenricError);

    void initPresenter(int pageNumber, int currentItens, int totalItems, int scrollOutItems);

    void initPresenter(RepositoryView repositoryView, List<Repository> repositories);

    void obtainNextReposotoriesPage(LinearLayoutManager mLayoutManager, boolean isScrolling, boolean isLastPage);

    void searchRepositories();

    void onDestroy();

}
