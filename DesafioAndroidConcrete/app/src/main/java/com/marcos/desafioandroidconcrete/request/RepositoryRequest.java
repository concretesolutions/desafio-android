package com.marcos.desafioandroidconcrete.request;

import com.marcos.desafioandroidconcrete.BuildConfig;
import com.marcos.desafioandroidconcrete.api.RepositoryApi;
import com.marcos.desafioandroidconcrete.domain.PullRequest;
import com.marcos.desafioandroidconcrete.domain.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RepositoryRequest {


    private static final RepositoryRequest INSTANCE = new RepositoryRequest();

    private RepositoryRequest() {
    }

    public static RepositoryRequest getInstance() {
        return INSTANCE;
    }

    private Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private RepositoryApi api = retrofit.create(RepositoryApi.class);

    public Call<Repository> fetchRepositories(String token, int page) {
        return api.getRepositories(token, page);
    }

    public Call<List<PullRequest>> fetchPullRequest(String token, String ownerName, String repositoryName) {

        return api.getPullRequest(token, ownerName, repositoryName);
    }


}
