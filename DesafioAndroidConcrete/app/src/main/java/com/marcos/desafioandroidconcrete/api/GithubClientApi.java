package com.marcos.desafioandroidconcrete.api;

import com.marcos.desafioandroidconcrete.domain.AcessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by marco on 17,Abril,2020
 */
public interface GithubClientApi {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AcessToken> getAcessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code
    );

}
