package com.github.api.morepopulargithubapp.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.api.morepopulargithubapp.R;
import com.github.api.morepopulargithubapp.adapter.PullRequestAdapter;
import com.github.api.morepopulargithubapp.model.vo.PullRequest;
import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.presenter.PullRequestPresenter;
import com.github.api.morepopulargithubapp.presenterImpl.PullRequestPresenterImpl;
import com.github.api.morepopulargithubapp.util.ViewUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;
import java.util.Map;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

@EActivity(R.layout.activity_pull_request)
public class PullRequestActivity extends AppCompatActivity implements PullRequestView {

    private LinearLayoutManager mLayoutManager;

    @Bean(PullRequestPresenterImpl.class)
    protected PullRequestPresenter pullRequestPresenter;

    @Bean
    protected PullRequestAdapter pullRequestAdapter;

    @ViewById
    protected RecyclerView recyclerViewPullRequest;

    @ViewById
    protected View progressPullResquest;

    @ViewById
    protected View areaErroPullRequest;

    @ViewById
    protected TextView msgErroPullRequest;

    @InstanceState
    protected List<PullRequest> pullRequests;

    @Extra
    @InstanceState
    protected Repository repository;

    @AfterViews
    void init() {
        msgErroPullRequest.setText(getString(R.string.error_list_pull_resquests));
        initRecyclerView();
        pullRequestPresenter.initPresenter(this, repository, pullRequests);
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPullRequest.setLayoutManager(mLayoutManager);
        recyclerViewPullRequest.setAdapter(pullRequestAdapter);
    }

    @Override
    public void setTitleActionBar(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showView(int recyclerViewVisibility, int progressVisibility, int areaErroVisibility) {
        recyclerViewPullRequest.setVisibility(recyclerViewVisibility);
        progressPullResquest.setVisibility(progressVisibility);
        areaErroPullRequest.setVisibility(areaErroVisibility);
    }

    @UiThread
    @Override
    public void showPullRequests(List<PullRequest> pullRequests) {
        showView(VISIBLE, GONE, GONE);
        this.pullRequests = pullRequests;
        pullRequestAdapter.setItems(this.pullRequests);
        pullRequestAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessageNotFound() {
        showView(GONE, GONE, GONE);
        ViewUtil.alert(this, getString(R.string.nao_existe_pull_request));
    }

    @UiThread
    @Override
    public void showError(Map errorMap) {
        showView(GONE, GONE, VISIBLE);
    }

    @Click(R.id.areaErroPullRequest)
    protected void reloadRepositories() {
//        searchPullRequest();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pullRequestPresenter.onDestroy();
    }
}
