package cess.com.br.androidchallenge.Data.Api;

import javax.inject.Inject;

import cess.com.br.androidchallenge.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    @Inject
    public RetrofitConfig() {
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.GITHUB_REPOS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RetrofitServices githubService() {
        return retrofit.create(RetrofitServices.class);
    }
}
