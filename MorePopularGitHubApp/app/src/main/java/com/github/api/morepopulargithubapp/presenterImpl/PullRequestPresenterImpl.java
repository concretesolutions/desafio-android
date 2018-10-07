package com.github.api.morepopulargithubapp.presenterImpl;

import android.content.Context;
import android.text.TextUtils;

import com.github.api.morepopulargithubapp.model.vo.PullRequest;
import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.model.web.PullVolleyRequest;
import com.github.api.morepopulargithubapp.presenter.PresenterApiCallBack;
import com.github.api.morepopulargithubapp.presenter.PullRequestPresenter;
import com.github.api.morepopulargithubapp.view.PullRequestView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

@EBean
public class PullRequestPresenterImpl implements PullRequestPresenter {

    private PresenterApiCallBack presenterApiCallBack;

    private PullRequestView pullRequestView;
    private Repository repository;

    protected Context context;

    @Bean
    protected PullVolleyRequest pullVolleyRequest;

    @Override
    public void initPresenter(PullRequestView pullRequestView, Repository repository, List<PullRequest> pullRequests) {
        this.pullRequestView = pullRequestView;
        this.repository = repository;
        initPresenterApiCallBack();

        if (repository != null && !TextUtils.isEmpty(repository.getName())) {
            this.pullRequestView.setTitleActionBar(repository.getName());
        }

        if (CollectionUtils.isNotEmpty(pullRequests)) {
            showPullRequests(pullRequests);
        } else {
            searchPullRequest();
        }
    }

    private void showPullRequests(List<PullRequest> pullRequests) {
        // Verifica se existe pullRequest para o repositório
        if (CollectionUtils.isNotEmpty(pullRequests)) {
            pullRequestView.showPullRequests(pullRequests);
        } else {
            pullRequestView.showMessageNotFound();
        }
    }

    private void initPresenterApiCallBack() {
        presenterApiCallBack = new PresenterApiCallBack() {
            @Override
            public void notifySuccess(Object object) {
                // Verifica se o presenter e por sua vez a activity não foi destruída
                if (presenterApiCallBack != null) {
                    List<PullRequest> pullRequests = (List<PullRequest>) object;
                    showPullRequests(pullRequests);
                }
            }

            @Override
            public void notifyError(Map error) {
                // Verifica se o presenter e por sua vez a activity não foi destruída
                if (presenterApiCallBack != null) {
                     pullRequestView.showError(error);
                }
            }
        };
    }


    @Override
    public void searchPullRequest() {
        pullRequestView.showView(GONE, VISIBLE, GONE);
        pullVolleyRequest.requestPullRequest(presenterApiCallBack, repository);
    }

    @Override
    public void onDestroy() {

        presenterApiCallBack = null;
    }
}
