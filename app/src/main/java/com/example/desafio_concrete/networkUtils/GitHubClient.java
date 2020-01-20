package com.example.desafio_concrete.networkUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubClient {

    private static Retrofit mRetrofit=null;

    private static final String GIT_URL = "https://api.github.com/";

    /**
     * Method get a retrofit client
     * @return a retrofit object
     */
    public static Retrofit getClient(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(GIT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
