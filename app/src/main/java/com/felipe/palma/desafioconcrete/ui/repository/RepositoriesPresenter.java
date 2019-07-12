package com.felipe.palma.desafioconcrete.ui.repository;

import com.felipe.palma.desafioconcrete.domain.response.RepositoriesResponse;
import com.felipe.palma.desafioconcrete.network.IServiceGithub;
import com.felipe.palma.desafioconcrete.network.ServiceGithubImp;
import com.felipe.palma.desafioconcrete.utils.Config;

/**
 * Created by Felipe Palma on 11/07/2019.
 */
public class RepositoriesPresenter implements RepositoriesContract.Presenter{

    private RepositoriesContract.View mView;

    public RepositoriesPresenter(RepositoriesContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadRepositories(int page) {
        this.mView.showDialog();
        ServiceGithubImp mServiceGithubImp = new ServiceGithubImp();
        mServiceGithubImp.getListRepository(Config.PARAM_QUERY, Config.PARAM_SORT, page, new IServiceGithub.IServiceCallback<RepositoriesResponse>() {
            @Override
            public void onSuccess(RepositoriesResponse repositoriesResponse) {
                mView.showRepositories(repositoriesResponse.getItems());
                mView.hideDialog();
            }

            @Override
            public void onError(String error) {
                mView.showError();
                mView.hideDialog();
            }
        });

    }
}
