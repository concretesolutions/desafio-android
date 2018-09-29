package cess.com.br.androidchallenge.UI.Contracts;

import cess.com.br.androidchallenge.Model.Remote.Repositories;
import cess.com.br.androidchallenge.UI.Adapters.RepoListAdapter;
import cess.com.br.androidchallenge.UI.BasePresenter;
import cess.com.br.androidchallenge.UI.BaseView;

public interface RepoContract {
    interface View extends BaseView<RepoContract.Presenter> {

        void setupRecyclerView(RepoListAdapter repoListAdapter);

        void populateList(Repositories repositories);
        void loadMoreRepos(Repositories repositories);

        void showCenterLoader();
        void hideCenterLoader();

        void showFooterLoader();
        void hideFooterLoader();

        void showRepoList();
        void hideRepoList();

        void performPagination();

        void showErrorMessage(int errorCode);
        void hideErrorMessage();

        void tryAgain();

    }

    interface Presenter extends BasePresenter {
        void getFirstPage(String page, String sort, String language);
        void getNextPage(String page, String sort, String language);
    }
}
