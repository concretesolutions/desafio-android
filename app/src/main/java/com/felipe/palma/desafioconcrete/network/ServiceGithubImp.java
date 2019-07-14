package com.felipe.palma.desafioconcrete.network;


import com.felipe.palma.desafioconcrete.domain.response.PullRequestResponse;
import com.felipe.palma.desafioconcrete.domain.response.RepositoriesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Felipe Palma on 11/07/2019.
 */
public class ServiceGithubImp implements IServiceGithub {


    private IServiceGithubEndPoint mService = RetrofitInstance.getInstance().getService();

    @Override
    public void getListRepository(String query, String sort, int page, IServiceCallback<RepositoriesResponse> callback) {


        Call<RepositoriesResponse> mCall = mService.getRepositoryList(query,sort,page);

        mCall.enqueue(new Callback<RepositoriesResponse>() {
            @Override
            public void onResponse(Call<RepositoriesResponse> call, Response<RepositoriesResponse> response) {
                if(!response.isSuccessful()){
                    callback.onError("Ocorreu um erro: " + response.errorBody().toString() );
                    return;
                }
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RepositoriesResponse> call, Throwable t) {
                callback.onError(t.getMessage());

            }
        });



    }

    @Override
    public void getPullRequests(String owner, String repo, IServiceCallback<List<PullRequestResponse>> callback) {


        Call<List<PullRequestResponse>> mCall = mService.getPullRequestList(owner,repo);

        mCall.enqueue(new Callback<List<PullRequestResponse>>() {
            @Override
            public void onResponse(Call<List<PullRequestResponse>> call, Response<List<PullRequestResponse>> response) {
                if(!response.isSuccessful()){
                    callback.onError("Ocorreu um erro: " + response.errorBody().toString() );
                    return;
                }
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<PullRequestResponse>> call, Throwable t) {
                callback.onError(t.getMessage());

            }
        });
    }
}
