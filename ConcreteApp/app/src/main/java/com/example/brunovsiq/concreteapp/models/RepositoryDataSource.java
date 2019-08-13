package com.example.brunovsiq.concreteapp.models;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.brunovsiq.concreteapp.AppController;
import com.example.brunovsiq.concreteapp.networking.GithubService;
import com.example.brunovsiq.concreteapp.screens.MainActivityViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PositionalDataSource;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepositoryDataSource extends PageKeyedDataSource<Integer, Repository> {

    GithubService service;
    AppController appController;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Repository> callback) {

//        appController.getRestApi().listRepos(1)
//                .enqueue(new Callback<JSONObject>() {
//                    @Override
//                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                        if (response.isSuccessful()) {
//                            createRepoList(response.body());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<JSONObject> call, Throwable t) {
//
//                    }
//                });
        //show progress bar
        AndroidNetworking.get("https://api.github.com/search/repositories?q=language:Java&sort=stars&page={pageNumber}")
                .addPathParameter("pageNumber", "0")
                //.addQueryParameter("limit", "3")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("total_count")) {
                            ArrayList<Repository> repositoryArrayList;
                            repositoryArrayList = createRepoList(response);
                            //MainActivityViewModel.repositoryPagedList.getValue();
                            callback.onResult(repositoryArrayList, 1, 2);
                            //repositoryArrayList.clear();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repository> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Repository> callback) {
//        appController.getRestApi().listRepos(params.key)
//                .enqueue(new Callback<JSONObject>() {
//                    @Override
//                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                        if (response.isSuccessful()) {
//                            createRepoList(response.body());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<JSONObject> call, Throwable t) {
//
//                    }
//                });
        AndroidNetworking.get("https://api.github.com/search/repositories?q=language:Java&sort=stars&page={pageNumber}")
                .addPathParameter("pageNumber", String.valueOf(params.key))
                //.addQueryParameter("limit", "3")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("total_count")) {
                            ArrayList<Repository> repositoryArrayList;
                            repositoryArrayList = createRepoList(response);
                            //MainActivityViewModel.repositoryPagedList.getValue();
                            callback.onResult(repositoryArrayList, params.key + 1);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private ArrayList<Repository> createRepoList(JSONObject response) {

        ArrayList<Repository> repoList = new ArrayList<>();
        Repository repository;

        //epository = Repository.fromJson(response.getJSONArray("items"));
        JSONArray jsonArray = null;
        try {
            jsonArray = response.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                repository = new Repository(jsonArray.getJSONObject(i));
                repoList.add(repository);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // send back to PagedList handler
        //callbackonResult(tweets);
        return repoList;

    }

}
