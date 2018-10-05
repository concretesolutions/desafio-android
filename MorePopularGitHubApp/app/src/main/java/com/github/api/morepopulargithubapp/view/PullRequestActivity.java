package com.github.api.morepopulargithubapp.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.api.morepopulargithubapp.R;
import com.github.api.morepopulargithubapp.adapter.PullRequestAdapter;
import com.github.api.morepopulargithubapp.model.vo.PullRequest;
import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.util.ViewUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

@EActivity(R.layout.activity_pull_request)
public class PullRequestActivity extends AppCompatActivity {

//    @Bean
//    PullRequestBO pullRepositoryBO;

    @Bean
    PullRequestAdapter repositoryAdapter;

    @ViewById
    RecyclerView recyclerViewPullRequest;

    @ViewById
    View progressPullResquest;

    @ViewById
    View areaErroPullRequest;

    @ViewById
    TextView msgErroPullRequest;

    @InstanceState
    protected List<PullRequest> pullRequests;

    @Extra
    protected Repository repository;

    private LinearLayoutManager mLayoutManager;


    @AfterViews
    void init() {
        msgErroPullRequest.setText(getString(R.string.error_list_pull_resquests));
        String nameRepository = repository.getName();
        if (repository != null && !TextUtils.isEmpty(nameRepository)) {
            getSupportActionBar().setTitle(nameRepository);
        }

        initRecyclerView();

        if (CollectionUtils.isNotEmpty(pullRequests)) {
            showList(pullRequests);
        } else {
//            searchPullRequest();
        }
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPullRequest.setLayoutManager(mLayoutManager);
        recyclerViewPullRequest.setAdapter(repositoryAdapter);
    }

//    private void searchPullRequest() {
//        showView(progressPullResquest);
//
//        pullRepositoryBO.RequestPullRequest(new ApiCallBack() {
//            @Override
//            public void onSuccess(Object response) {
//                showList((List<PullRequest>) response);
//            }
//
//            @Override
//            public void onError(Map error) {
//                showError(error);
//            }
//
//        }, repository);
//    }

    void showView(View view) {
        recyclerViewPullRequest.setVisibility(View.GONE);
        progressPullResquest.setVisibility(View.GONE);
        areaErroPullRequest.setVisibility(View.GONE);

        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }


    @UiThread
    protected void showList(List<PullRequest> pullRequests) {
        // Verifica se existe pullRequest para o repositório
        if (CollectionUtils.isNotEmpty(pullRequests)) {
            showView(recyclerViewPullRequest);
            this.pullRequests = pullRequests;
            repositoryAdapter.setItems(this.pullRequests);
            repositoryAdapter.notifyDataSetChanged();
        } else {
            showView(null);
            ViewUtil.alert(this, getString(R.string.nao_existe_pull_request));
        }
    }

    @UiThread
    protected void showError(Map errorMap) {
        showView(areaErroPullRequest);
    }

    @Click(R.id.areaErroPullRequest)
    protected void reloadRepositories() {
//        searchPullRequest();
    }

}
