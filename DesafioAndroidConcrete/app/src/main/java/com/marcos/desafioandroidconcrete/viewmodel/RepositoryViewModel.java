package com.marcos.desafioandroidconcrete.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marcos.desafioandroidconcrete.domain.Repository;
import com.marcos.desafioandroidconcrete.request.RepositoryRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marco on 19,Abril,2020
 */
public class RepositoryViewModel extends ViewModel {
    public MutableLiveData<Repository> repositoryMutableLiveData = new MutableLiveData<>();

    public void getRepositories(String token, int page) {


        RepositoryRequest.getInstance().fetchRepositories(token, page).enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                if (response.isSuccessful()) {
                    repositoryMutableLiveData.setValue(response.body());
                } else {
                    repositoryMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                repositoryMutableLiveData.setValue(null);

            }
        });
    }

}
