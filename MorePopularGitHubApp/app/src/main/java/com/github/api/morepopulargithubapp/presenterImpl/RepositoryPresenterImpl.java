package com.github.api.morepopulargithubapp.presenterImpl;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.model.web.RepositoryRequest;
import com.github.api.morepopulargithubapp.presenter.PresenterApiCallBack;
import com.github.api.morepopulargithubapp.presenter.RepositoryPresenter;
import com.github.api.morepopulargithubapp.view.RepositoryView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.github.api.morepopulargithubapp.util.ConstantsUitl.SERVER_UNABLE_PROCESS_INSTRUCTIONS_CODE;

@EBean
public class RepositoryPresenterImpl implements RepositoryPresenter {

    private PresenterApiCallBack presenterApiCallBack;
    private RepositoryView repositoryView;
    private boolean isChangingOrientation, isScrolling, isLastPage, isGenricError;
    private int pageNumber ,currentItens, totalItems, scrollOutItems;

    @RootContext
    protected Context context;

    @Bean
    protected RepositoryRequest repositoryRequest;


    @Override
    public void initPresenter(boolean isChangeOrientation, boolean isScrolling, boolean isLastPage, boolean isGenricError) {
        this.isChangingOrientation = isChangeOrientation;
        this.isScrolling = isScrolling;
        this.isLastPage = isLastPage;
        this.isGenricError = isGenricError;
    }

    @Override
    public void initPresenter(int pageNumber, int currentItens, int totalItems, int scrollOutItems) {
        this.pageNumber = pageNumber;
        this.currentItens = currentItens;
        this.totalItems = totalItems;
        this.scrollOutItems = scrollOutItems;
    }

    @Override
    public void initPresenter(RepositoryView repositoryView, List<Repository> repositories) {
        this.repositoryView = repositoryView;
        initPresenterApiCallBack();

        if (CollectionUtils.isNotEmpty(repositories)) {
            isChangingOrientation = true;
            showRepositories(repositories);
        } else {
            pageNumber = 1;
            searchRepositories(isGenricError);
        }
    }

    private void initPresenterApiCallBack() {
        presenterApiCallBack = new PresenterApiCallBack() {
            @Override
            public void notifySuccess(Object object) {
                // Verifica se o presenter e por sua vez a activity não foi destruída
                if (presenterApiCallBack != null) {
                    List<Repository> repositories = (List<Repository>) object;
                    showRepositories(repositories);
                }
            }

            @Override
            public void notifyError(Map error) {
                // Verifica se o presenter e por sua vez a activity não foi destruída
                if (presenterApiCallBack != null) {
                    showError(error);
                }
            }
        };
    }

    private void showRepositories(List<Repository> repositories) {
        if (pageNumber == 1 || isChangingOrientation) {
            isChangingOrientation = false;

            repositoryView.showView(VISIBLE, GONE, GONE, GONE);
            repositoryView.showRepositories(repositories, isChangingOrientation);
        }
        // Verifica se a requisição foi chamda a partir da área erro em busca da próxima página
        else if (isGenricError && !isChangingOrientation) {
            repositoryView.showView(VISIBLE, GONE, GONE, VISIBLE);
            isGenricError = false;
            repositoryView.showAddMoreRepositories(repositories);
        } else {
            repositoryView.showView(VISIBLE, GONE, GONE, GONE);
            repositoryView.showAddMoreRepositories(repositories);
        }
    }

    private void showError(Map errorMap){
        // Verifica se o erro retornado é 422
        if (pageNumber != 1 && errorMap != null && errorMap.containsKey(SERVER_UNABLE_PROCESS_INSTRUCTIONS_CODE)) {
            isLastPage = true;
            repositoryView.showView(VISIBLE, GONE, GONE, GONE);
            String errorMessage = (String) errorMap.get(SERVER_UNABLE_PROCESS_INSTRUCTIONS_CODE);
            // Não será mais disponível uma outra página
            repositoryView.showMessageError(errorMessage, isLastPage);
        } else {
            repositoryView.showError(errorMap);
        }
    }

    @Override
    public void obtainNextReposotoriesPage(LinearLayoutManager mLayoutManager, boolean isScrolling, boolean isLastPage) {

        currentItens = mLayoutManager.getChildCount();
        totalItems = mLayoutManager.getItemCount();
        scrollOutItems = mLayoutManager.findFirstVisibleItemPosition();
        this.isScrolling = isScrolling;

        // Verifica se foi feito um scroll, se está no ultimo registro e
        // se ultima página de repósitorios não foi obtida
        if (this.isScrolling && (currentItens + scrollOutItems == totalItems) && !isLastPage) {
            this.isScrolling = false;
            // Informar o valor atual do scorlling para view
            repositoryView.setIsScrolling(this.isScrolling);
            // Incrementa próxima página
            pageNumber++;
            // Obtem próxima página de repositorios
            searchRepositories(isGenricError);
        }
    }

    @Override
    public void searchRepositories(boolean isGenricError) {
        if (pageNumber == 1 || isGenricError) {
            repositoryView.showView(GONE, VISIBLE, GONE, GONE);
        } else {
            repositoryView.showView(VISIBLE, GONE, GONE, VISIBLE);
        }
        repositoryRequest.requestRepositorie(presenterApiCallBack, pageNumber);
    }


    @Override
    public void onDestroy() {
        presenterApiCallBack = null;
    }
}
