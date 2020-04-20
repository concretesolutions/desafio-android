package com.marcos.desafioandroidconcrete.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marco on 17,Abril,2020
 */
public class AcessToken {

    @SerializedName("access_token")
    private String accessToken = "";
    @SerializedName("token_type")
    private String tokenType = "";

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
