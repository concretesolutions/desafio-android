package com.github.api.morepopulargithubapp.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.github.api.morepopulargithubapp.R;
import com.github.api.morepopulargithubapp.adapter.RepositoryAdapter;
import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.presenter.RepositoryPresenter;
import com.github.api.morepopulargithubapp.presenterImpl.RepositoryPresenterImpl;
import com.github.api.morepopulargithubapp.util.ViewUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;
import java.util.Map;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements RepositoryView {

    private boolean isChangingOrientation = false;

    private LinearLayoutManager mLayoutManager;

    @Bean(RepositoryPresenterImpl.class)
    protected RepositoryPresenter repositoryPresenter;

    @Bean
    protected RepositoryAdapter repositoryAdapter;

    @ViewById
    protected RecyclerView recyclerView;

    @ViewById
    protected View progress;

    @ViewById
    protected View fetchDataProgress;

    @ViewById
    protected View areaErro;

    @ViewById
    protected TextView textMsgErroView;

    @InstanceState
    protected List<Repository> repositories;

    @InstanceState
    protected boolean isScrolling, isLastPage, isGenricError = false;

    @InstanceState
    protected int pageNumber, currentItens, totalItems, scrollOutItems;

    @AfterViews
    void init() {
        textMsgErroView.setText(getString(R.string.error_list_repository));
        initRecyclerView();
        repositoryPresenter.initPresenter(isChangingOrientation, isScrolling, isLastPage, isGenricError);
        repositoryPresenter.initPresenter(pageNumber, currentItens,totalItems, scrollOutItems);
        repositoryPresenter.initPresenter(this, repositories);
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(repositoryAdapter);
        addInfitePagination();
    }

    private void addInfitePagination() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                repositoryPresenter.obtainNextReposotoriesPage(mLayoutManager,isScrolling, isLastPage);
            }
        });
    }

    @Override
    public void setIsScrolling(boolean isScrolling) {
        this.isScrolling = isScrolling;
    }

    @Override
    public void showView(int recyclerViewVisibility, int progressVisibility,
                         int areaErroVisibility, int fetchDataProgressVisibility) {

        recyclerView.setVisibility(recyclerViewVisibility);
        progress.setVisibility(progressVisibility);
        areaErro.setVisibility(areaErroVisibility);
        fetchDataProgress.setVisibility(fetchDataProgressVisibility);
    }

    @UiThread
    @Override
    public void showRepositories(List<Repository> repositories, boolean isChangingOrientation) {
        this.repositories = repositories;
        repositoryAdapter.setItems(this.repositories);
        this.isChangingOrientation = isChangingOrientation;
        repositoryAdapter.notifyDataSetChanged();
    }

    @UiThread
    @Override
    public void showAddMoreRepositories(List<Repository> repositories) {
        this.repositories.addAll(repositories);
        repositoryAdapter.notifyDataSetChanged();
    }

    @UiThread
    @Override
    public void showError(Map errorMap) {
        showView(GONE, GONE, VISIBLE, GONE);
    }

    @UiThread
    @Override
    public void showMessageLimitMaxRecords(String errorMessage, boolean isLastPage) {
        this.isLastPage = isLastPage;
        ViewUtil.alert(this, errorMessage);
    }


    @Click(R.id.areaErro)
    protected void reloadRepositories() {
        isGenricError = true;
        repositoryPresenter.searchRepositories(isGenricError);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repositoryPresenter.onDestroy();
    }
}
