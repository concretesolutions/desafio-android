package com.example.desafioandroid.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.desafioandroid.feature.endpoints.GetDataService;
import com.example.desafioandroid.feature.utilities.RetrofitClientInstance;
import com.example.desafioandroid.model.Base;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseRepository {


    //Retrofit Interface
    private GetDataService service;

    //MutableLiveData
    private MutableLiveData<Base> items;


    public LiveData<Base> getBase(int currentPage) {
        if (items == null) {
            items = new MutableLiveData<>();
            bases(currentPage);
        }

        return items;
    }

    public void bases(int currentPage){

        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Base> call = service.getBaseResponse(currentPage);

        call.enqueue(new Callback<Base>() {
            @Override
            public void onResponse(Call<Base> call, Response<Base> response) {
                Base base = response.body();
                items.setValue(base);
                Log.d("TESTE", base.getRepositories().get(0).getName());
            }

            @Override
            public void onFailure(Call<Base> call, Throwable t) {

            }
        });
    }
}
