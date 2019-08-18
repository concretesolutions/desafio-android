package br.com.desafioandroid.wsconsumer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public static final String preferencias = "preferencias";
    public static final String objOffline = "objOffline";

    public RetrofitConfig() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiService getService() {
        return RetrofitConfig.this.retrofit.create(ApiService.class);
    }

}
