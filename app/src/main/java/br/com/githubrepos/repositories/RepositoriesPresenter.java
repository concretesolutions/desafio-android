package br.com.githubrepos.repositories;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import br.com.githubrepos.data.entity.Repository;
import br.com.githubrepos.data.entity.RepositoryStatus;
import br.com.githubrepos.data.service.RepositoryServiceApi;

public class RepositoriesPresenter implements RepositoriesContract.UserActionsListener {

    private RepositoryServiceApi mServiceApi;
    private RepositoriesContract.View mView;

    public RepositoriesPresenter(@NonNull RepositoryServiceApi repositoryServiceApi,
                                 @NonNull RepositoriesContract.View repositoriesView) {

        this.mServiceApi = Preconditions.checkNotNull(repositoryServiceApi,
                "mServiceApi cannot be null!");
        this.mView = Preconditions.checkNotNull(repositoriesView,
                "mView cannot be null!");
    }

    @Override
    public void loadRepositoryList(int page, final boolean doRefresh) {
        String language = "language:Java", sort = "stars";

        if(doRefresh) {
            mView.setProgressIndicator(true);
        } else {
            mView.setListProgressIndicator(true);
        }

        mServiceApi.search(page, language, sort, new RepositoryServiceApi.RepositoryServiceCallback<RepositoryStatus>() {
            @Override
            public void onLoaded(RepositoryStatus data) {

                if(doRefresh) {
                    mView.setProgressIndicator(false);
                } else {
                    mView.setListProgressIndicator(false);
                }

                mView.showRepositoryList(data.getRepositoryList(), doRefresh);
            }
        });

    }

    @Override
    public void openRepository(Repository repository) {
        mView.showPullRequestListUi(repository.getOwner().getLogin(), repository.getName());
    }
}
