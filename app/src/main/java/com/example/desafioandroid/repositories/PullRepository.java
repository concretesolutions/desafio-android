package com.example.desafioandroid.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.desafioandroid.feature.endpoints.GetDataService;
import com.example.desafioandroid.feature.utilities.RetrofitClientInstance;
import com.example.desafioandroid.model.Base;
import com.example.desafioandroid.model.Pull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRepository {

    //Retrofit Interface
    private GetDataService service;

    //Mutable LiveData
    private MutableLiveData<List<Pull>> items;

    public LiveData<List<Pull>> getPulls(String user, String repository){
        if (items == null){
            items = new MutableLiveData<>();
            pulls(user, repository);
        }

        return items;
    }

    private void pulls(String user, String repository){
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Pull>> call = service.getPullsResponse(user, repository);

        call.enqueue(new Callback<List<Pull>>() {
            @Override
            public void onResponse(Call<List<Pull>> call, Response<List<Pull>> response) {
                List<Pull> pulls = response.body();
                items.setValue(pulls);
            }

            @Override
            public void onFailure(Call<List<Pull>> call, Throwable t) {

            }
        });
    }
}
