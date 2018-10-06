package com.github.api.morepopulargithubapp.view;

import com.github.api.morepopulargithubapp.model.vo.Repository;

import java.util.List;
import java.util.Map;

public interface RepositoryView {

    void showView(int recyclerViewVisibility, int progressVisibility, int areaErroVisibility, int fetchDataProgressVisibility);

    void showRepositories(List<Repository> repositories);

    void showAddMoreRepositories(List<Repository> repositories);

    void showError(Map errorMap);

    void showMessageError(String errorMessage);
}
