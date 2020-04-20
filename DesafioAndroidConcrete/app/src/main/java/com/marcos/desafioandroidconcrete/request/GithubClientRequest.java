package com.marcos.desafioandroidconcrete.request;

import com.marcos.desafioandroidconcrete.BuildConfig;
import com.marcos.desafioandroidconcrete.api.GithubClientApi;
import com.marcos.desafioandroidconcrete.domain.AcessToken;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marco on 30,Janeiro,2020
 */
public class GithubClientRequest {


    private static final GithubClientRequest INSTANCE = new GithubClientRequest();

    private GithubClientRequest() {
    }

    public static GithubClientRequest getInstance() {
        return INSTANCE;
    }

    private Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private GithubClientApi api = retrofit.create(GithubClientApi.class);

    public Call<AcessToken> getAcessToken(
                              String clientId,
                              String clientSecret,
                              String code) {
               return api.getAcessToken(clientId, clientSecret, code);
           }


}
