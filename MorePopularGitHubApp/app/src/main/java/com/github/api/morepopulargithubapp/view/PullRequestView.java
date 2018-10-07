package com.github.api.morepopulargithubapp.view;

import com.github.api.morepopulargithubapp.model.vo.PullRequest;

import java.util.List;
import java.util.Map;

public interface PullRequestView {


    void setTitleActionBar(String title);

    void showView(int recyclerViewVisibility, int progressVisibility, int areaErroVisibility) ;

    void showPullRequests(List<PullRequest> pullRequests);

    void showMessageNotFound( );

    void showError(Map errorMap);
}
