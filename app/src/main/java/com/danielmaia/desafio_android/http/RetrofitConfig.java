package com.danielmaia.desafio_android.http;

import com.danielmaia.desafio_android.AppRepo;
import com.danielmaia.desafio_android.R;
import com.danielmaia.desafio_android.service.list.ListService;
import com.danielmaia.desafio_android.service.pull.PullService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(AppRepo.getInstance().getResources().getString(R.string.BASE_URL))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ListService.ListServiceRequest getJavaList() {
        return this.retrofit.create(ListService.ListServiceRequest.class);
    }

    public PullService.ListPullRequest getPullList() {
        return this.retrofit.create(PullService.ListPullRequest.class);
    }


}












