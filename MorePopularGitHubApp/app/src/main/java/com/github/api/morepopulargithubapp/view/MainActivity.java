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
import com.github.api.morepopulargithubapp.util.ViewUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

//    @Bean
//    RepositoryBO repositoryBO;
//
    @Bean
    RepositoryAdapter repositoryAdapter;

    @ViewById
    RecyclerView recyclerView;

    @ViewById
    View progress;

    @ViewById
    View fetchDataProgress;

    @ViewById
    View areaErro;

    @ViewById
    TextView textMsgErroView;

    @InstanceState
    protected List<Repository> repositories;

    @InstanceState
    protected int pageNumber;

    protected LinearLayoutManager mLayoutManager;

    @InstanceState
    protected boolean isScrolling, isLastPage, isErrorGenric = false;

    private boolean isChangingOrientation = false;

    @InstanceState
    protected int currentItens, totalItems, scrollOutItems;

    @AfterViews
    void init() {
        textMsgErroView.setText(getString(R.string.error_list_repository));
        initRecyclerView();

        if (CollectionUtils.isNotEmpty(repositories)) {
            isChangingOrientation = true;
            showList(repositories);
        } else {
            pageNumber = 1;
            searchRepositories();
        }
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

                currentItens = mLayoutManager.getChildCount();
                totalItems = mLayoutManager.getItemCount();
                scrollOutItems = mLayoutManager.findFirstVisibleItemPosition();

                // Verifica se foi feito um scroll, se está no ultimo registro e
                // se ultima página de repósitorios não foi obtida
                if (isScrolling && (currentItens + scrollOutItems == totalItems) && !isLastPage) {
                    isScrolling = false;
                    // Incrementa próxima página
                    pageNumber++;
                    // Obtem próxima página de reppsitorios
                    searchRepositories();
                }
            }
        });
    }

    private void searchRepositories() {

        if (pageNumber == 1 || isErrorGenric) {
            showView(progress);
        } else {
            fetchDataProgress.setVisibility(View.VISIBLE);
        }

//        repositoryBO.RequestRepositorie(new ApiCallBack() {
//            @Override
//            public void onSuccess(Object response) {
//                showList((List<Repository>) response);
//            }
//
//            @Override
//            public void onError(Map error) {
//                showError(error);
//            }
//
//        }, pageNumber);
    }

    void showView(View view) {
        recyclerView.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        areaErro.setVisibility(View.GONE);
        fetchDataProgress.setVisibility(View.GONE);

        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }


    @UiThread
    protected void showList(List<Repository> repositories) {
        if (pageNumber == 1 || isChangingOrientation) {
            showView(recyclerView);
            this.repositories = repositories;
            repositoryAdapter.setItems(this.repositories);
            isChangingOrientation = false;
        }
        // Verifica se a requisição foi chamda a partir da área erro em busca da próxima página
        else if (isErrorGenric && !isChangingOrientation) {
            showView(recyclerView);
            this.repositories.addAll(repositories);
            isErrorGenric = false;
        } else {
            this.repositories.addAll(repositories);
            fetchDataProgress.setVisibility(View.GONE);
        }
        repositoryAdapter.notifyDataSetChanged();
    }

    @UiThread
    protected void showError(Map errorMap) {
        // Verifica se o erro retornado é 422
        if (pageNumber != 1 && errorMap != null && errorMap.containsKey(422)) {
            progress.setVisibility(View.GONE);
            fetchDataProgress.setVisibility(View.GONE);
            String errorMessage = (String) errorMap.get(422);
            // Não será mais disponível uma outra página
            ViewUtil.alert(this, errorMessage);
            isLastPage = true;
        } else {
            showView(areaErro);
        }
    }

    @Click(R.id.areaErro)
    protected void reloadRepositories() {
        isErrorGenric = true;
        searchRepositories();
    }
}
