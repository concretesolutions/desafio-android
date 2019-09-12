package com.feliperamoscarvalho.appconsultagithub.repositories;

import android.support.annotation.NonNull;

import com.feliperamoscarvalho.appconsultagithub.data.RepositoryServiceAPI;
import com.feliperamoscarvalho.appconsultagithub.data.model.Item;
import com.feliperamoscarvalho.appconsultagithub.data.model.RepositorySearchResult;

/**
 * Presenter of the Repositories view (RepositoriesFragment). Implements RepositoriesContract
 * interface user actions.
 */
public class RepositoriesPresenter implements RepositoriesContract.UserActionsListener {

    private final RepositoryServiceAPI mApi;

    private final RepositoriesContract.View mRepositoriesView;

    /**
     * Constructor gets model (RepositoryServiceAPI) and view (RepositoriesContract.View).
     */
    public RepositoriesPresenter(RepositoryServiceAPI api, RepositoriesContract.View repositoriesView) {
        mApi = api;
        mRepositoriesView = repositoriesView;
    }

    /**
     * Method that loads repository data into the view, using the model function to fetch data.
     */
    @Override
    public void loadRepository() {

        mRepositoriesView.setLoading(true);

        mApi.getRepositories(new RepositoryServiceAPI.RepositoryServiceCallback<RepositorySearchResult>() {
            @Override
            public void onLoaded(RepositorySearchResult repositories) {
                mRepositoriesView.setLoading(false);
                mRepositoriesView.showRepositoryItems(repositories.getItems());
            }
        });

    }

    /**
     * Method that calls the view function to open the detail screen.
     *
     * @param repositories line object you want to open details
     */
    @Override
    public void openDetails(@NonNull Item repositories) {
        mRepositoriesView.showDetailsUI(repositories.getOwner().getLogin(), repositories.getName());
    }
}
