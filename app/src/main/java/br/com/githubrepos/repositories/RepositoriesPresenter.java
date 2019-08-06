package br.com.githubrepos.repositories;


import androidx.annotation.NonNull;

import br.com.githubrepos.data.entity.Repository;
import br.com.githubrepos.data.entity.RepositoryStatus;
import br.com.githubrepos.data.service.RepositoryServiceApi;

public class RepositoriesPresenter implements RepositoriesContract.UserActionsListener {

    private RepositoryServiceApi mServiceApi;
    private RepositoriesContract.View mView;

    private int selectedRepositoryPosition;

    public RepositoriesPresenter(@NonNull RepositoryServiceApi repositoryServiceApi,
                                 @NonNull RepositoriesContract.View repositoriesView) {

        this.mServiceApi = repositoryServiceApi;
        this.mView = repositoriesView;

        this.selectedRepositoryPosition = -1;
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

            @Override
            public void onError(String errorMsg) {
                //TODO tratamento de erro
            }
        });

    }

    @Override
    public void openRepository(Repository repository) {
        mView.showPullRequestListUi(repository.getOwner().getLogin(), repository.getName());
    }

    @Override
    public void selectRepository(int selectedRepositoryPosition) {
        this.selectedRepositoryPosition = selectedRepositoryPosition;
        mView.changeActionBarWhenRepositorySelected();
    }

    @Override
    public void unselectRepository(int selectedRepositoryPosition) {
        mView.changeActionBarWhenRepositoryUnselected(this.selectedRepositoryPosition);
        this.selectedRepositoryPosition = -1;
    }

    @Override
    public void deleteSelectedRepository() {
        mView.removeRepository(this.selectedRepositoryPosition);
    }
}
