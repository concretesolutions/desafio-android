package com.felipe.palma.desafioconcrete.network;

import com.felipe.palma.desafioconcrete.domain.response.PullRequestResponse;
import com.felipe.palma.desafioconcrete.domain.response.RepositoriesResponse;

import java.util.List;


/**
 * Created by Felipe Palma on 11/07/2019.
 */
public interface IServiceGithub {

    interface IServiceCallback<T>{
        void onSuccess(T t);
        void onError(String error);
    }

    void getListRepository(String q, String sort, int page,
                           IServiceCallback<RepositoriesResponse> callback);

    void getPullRequests(String owner, String repo,
                         IServiceCallback<List<PullRequestResponse>> callback);


}
