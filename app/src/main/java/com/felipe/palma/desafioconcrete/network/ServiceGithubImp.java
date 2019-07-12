package com.felipe.palma.desafioconcrete.network;


import com.felipe.palma.desafioconcrete.domain.response.RepositoriesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Felipe Palma on 11/07/2019.
 */
public class ServiceGithubImp implements IServiceGithub {

    @Override
    public void getListRepository(String query, String sort, int page, IServiceCallback<RepositoriesResponse> callback) {

        IServiceGithubEndPoint mService = RetrofitInstance.getInstance().create(IServiceGithubEndPoint.class);

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
}
