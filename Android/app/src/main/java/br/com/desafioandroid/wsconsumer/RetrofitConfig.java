package br.com.desafioandroid.wsconsumer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiService getService() {
        return this.retrofit.create(ApiService.class);
    }

}
