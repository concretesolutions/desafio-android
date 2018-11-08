package com.example.luisguzman.desafio_android.adapter;

import com.example.luisguzman.desafio_android.interfaz.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

    private static Api API_SERVICE;

    public static Api getApiService() {

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API_SERVICE = retrofit.create(Api.class);
        }

        return API_SERVICE;
    }

}