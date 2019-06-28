package com.brunorfreitas.desafioconcrete.data.API;

import com.brunorfreitas.desafioconcrete.data.Model.PullRequestRepositoryResponse;
import com.brunorfreitas.desafioconcrete.data.Model.RepositoryResponse;

import java.util.List;

public interface ApiGit {
    interface ApiServiceCallback<T> {
        void onSucces(T result);
        void onFailure(String message);
    }

    void getListRepository(String q, String sort, int page,
                           ApiServiceCallback<RepositoryResponse> callback);

    void getPullRequestsRepository(String owner, String repo,
                                   ApiServiceCallback<List<PullRequestRepositoryResponse>> callback);
}
