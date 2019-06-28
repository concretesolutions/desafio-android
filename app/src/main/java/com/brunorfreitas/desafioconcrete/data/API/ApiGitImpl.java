package com.brunorfreitas.desafioconcrete.data.API;

import com.brunorfreitas.desafioconcrete.data.Model.PullRequestRepositoryResponse;
import com.brunorfreitas.desafioconcrete.data.Model.RepositoryResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGitImpl implements ApiGit {

    private ApiEndPointGitRetrofit INSTANCE = null;

    private ApiEndPointGitRetrofit getInstance(){

        if (INSTANCE == null){
            Gson gson = new GsonBuilder().serializeNulls().create();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
            //
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();

            INSTANCE = retrofit.create(ApiEndPointGitRetrofit.class);
        }
        return INSTANCE;
    }


    @Override
    public void getListRepository(String q, String sort, int page, final ApiServiceCallback<RepositoryResponse> callback) {
        Call<RepositoryResponse> call = getInstance().getRepositoryList(q, sort, page);

        call.enqueue(new Callback<RepositoryResponse>() {
            @Override
            public void onResponse(Call<RepositoryResponse> call, Response<RepositoryResponse> response) {
                if(!response.isSuccessful()){

                    callback.onFailure("Error code: " +response.code());
                    return;
                }

                RepositoryResponse repositoryResponse = response.body();
                callback.onSucces(repositoryResponse);
            }

            @Override
            public void onFailure(Call<RepositoryResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure("Error code: " +t.getMessage());
            }
        });
    }

    @Override
    public void getPullRequestsRepository(String owner, String repo, final ApiServiceCallback<List<PullRequestRepositoryResponse>> callback) {
        Call<List<PullRequestRepositoryResponse>> call =
                getInstance().getListPullRequestRepositoryResponse(owner, repo);

        call.enqueue(new Callback<List<PullRequestRepositoryResponse>>() {
            @Override
            public void onResponse(Call<List<PullRequestRepositoryResponse>> call, Response<List<PullRequestRepositoryResponse>> response) {
                if(!response.isSuccessful()){

                    callback.onFailure("Error code: " +response.code());
                    return;
                }

                List<PullRequestRepositoryResponse> pullRequestRepositoryResponseList = response.body();
                callback.onSucces(pullRequestRepositoryResponseList);
            }

            @Override
            public void onFailure(Call<List<PullRequestRepositoryResponse>> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure("Error code: " +t.getMessage());
            }
        });
    }
}
