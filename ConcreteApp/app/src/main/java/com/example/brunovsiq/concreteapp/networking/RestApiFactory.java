package com.example.brunovsiq.concreteapp.networking;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiFactory {

    private static String BASE_URL = "https://api.github.com/";

    public static RestApi create() {
        okhttp3.logging.HttpLoggingInterceptor logging = new okhttp3.logging.HttpLoggingInterceptor();
        logging.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit.create(RestApi.class);
    }
}
