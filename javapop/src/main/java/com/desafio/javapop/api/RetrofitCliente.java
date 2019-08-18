package com.desafio.javapop.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCliente {
    private static final String BASE_URL = "https://api.github.com/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public GitHubServico githubServico() { return retrofit.create(GitHubServico.class); }
}
