package com.github.api.morepopulargithubapp;

import android.support.annotation.NonNull;

import com.github.api.morepopulargithubapp.model.vo.PullRequest;
import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.presenter.PresenterApiCallBack;
import com.github.api.morepopulargithubapp.presenterImpl.PullRequestPresenterImpl;
import com.github.api.morepopulargithubapp.view.PullRequestView;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.List;
import java.util.Map;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class PullRequestPresenterTest {

    private PullRequestPresenterImpl presenterImpl;
    private PresenterApiCallBack presenterApiCallBack;
    private PullRequestView pullRequestView;
    private Repository repository;
    private String titleActionBar;
    private List <PullRequest> pullRequests;
    private String notFoundPullRequestMessage;

    @Before
    public void setUp() {
        initPullRequestViewTeste();
        presenterImpl = new PullRequestPresenterImpl();
    }

    private void initPullRequestViewTeste() {

        pullRequestView = new PullRequestView() {
            @Override
            public void setTitleActionBar(String title) {
                titleActionBar = title;
            }

            @Override
            public void showView(int recyclerViewVisibility, int progressVisibility, int areaErroVisibility) {

            }

            @Override
            public void showPullRequests(List<PullRequest> pullRequestsParam) {
                pullRequests = pullRequestsParam;
            }

            @Override
            public void showMessageNotFound() {
                notFoundPullRequestMessage = getNotFoundPullRequestMessage();
            }

            @Override
            public void showError(Map errorMap) {

            }
        };
    }

    @NonNull
    private String getNotFoundPullRequestMessage() {
        return ShadowApplication.getInstance().getApplicationContext().getString(R.string.nao_existe_pull_request);
    }

    /**
     * Valida o título informado a actionBar
     */
    @Test
    public void onInitView_validationTitleActionBar() {
        repository = new Repository();
        repository.setName("Repository Name");
        initView();

        Assert.assertEquals(titleActionBar, repository.getName());
    }

    @NonNull
    private void initView() {
        presenterImpl.initView(pullRequestView, repository);
    }

    /**
     * Valida os dados enviados de pull Requests enviados
     */
    @Test
    public void onShowPullRequests_validationExistisPullRequestsFromRepository() {
        initView();
        List<PullRequest> pullRequestsList = PullRequestMock.getPullRequestsMock();
        presenterImpl.showPullRequests(pullRequestsList);
        Assert.assertTrue(CollectionUtils.isEqualCollection(pullRequestsList, pullRequests));
    }

    /**
     * Valida a mensagem de pullRequest não encontrado ao informar que a lista de pullRequest está nula
     */
    @Test
    public void onShowPullRequests_validationNotFoundPullRequestsFromRepository() {
        initView();
        presenterImpl.showPullRequests(null);
        String message = getNotFoundPullRequestMessage();
        Assert.assertEquals(message, notFoundPullRequestMessage);
    }

}