package com.brunorfreitas.desafioconcrete.ui.ActListRepository;

import com.brunorfreitas.desafioconcrete.data.API.ApiGit;
import com.brunorfreitas.desafioconcrete.data.API.ApiGitImpl;
import com.brunorfreitas.desafioconcrete.data.Model.RepositoryResponse;

public class PresenterListRepository implements ContractListRepository.Presenter {

    private ContractListRepository.View view;
    private ApiGit apiGit;

    public PresenterListRepository(ContractListRepository.View view) {
        this.view = view;
        apiGit = new ApiGitImpl();
    }

    @Override
    public void loadListRepo(final int page) {

        view.showProgressBar();

        String q = "language:Java";
        String sort = "stars";

        apiGit.getListRepository(q, sort, page, new ApiGit.ApiServiceCallback<RepositoryResponse>() {
            @Override
            public void onSucces(RepositoryResponse result) {
                if (page>1){
                    view.addListRepo(result.getItems());
                    view.hideProgressBar();
                }else{
                    view.showRepoList(result.getItems());
                    view.hideProgressBar();
                }

            }

            @Override
            public void onFailure(String message) {
                view.showError(message);
                view.hideProgressBar();
            }
        });

    }
}
