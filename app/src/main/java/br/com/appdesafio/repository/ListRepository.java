package br.com.appdesafio.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.com.appdesafio.model.pojo.PullRequest;
import br.com.appdesafio.model.pojo.Repository;
import br.com.appdesafio.service.IService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class repository that makes API calls.
 */
public class ListRepository {

    public IService mIservice;
    public Context mContext;


    @Inject
    public ListRepository(final  IService service,
                          final Application application
                  ){
        this.mIservice = service;
        this.mContext = application;


    }

    /**
     * method responsible for logging in to the server.
     *
     * @param
     * @return
     */
    public LiveData<Repository> getListRepository(final int page) {
        final MutableLiveData<Repository> data = new MutableLiveData<>();
        final Call<JsonObject> call = this.mIservice.getListRepository("language:Java", "stars", String.valueOf(page));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(final Call<JsonObject> call, final Response<JsonObject> response) {
                Gson gson = new Gson();
                Repository repository = gson.fromJson(response.body(), Repository.class);
                data.setValue(repository);

            }


            @Override
            public void onFailure(final Call<JsonObject> call, final Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    /**
     * method responsible for logging in to the server.
     *
     * @param
     * @return
     */
    public LiveData<List<PullRequest>> getListPullRequest(final String creator, final String repository) {
        final MutableLiveData<List<PullRequest> > data = new MutableLiveData<>();
        final Call<JsonArray> call = this.mIservice.getListPullRequest(creator, repository);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(final Call<JsonArray> call, final Response<JsonArray> response) {
                Gson gson = new Gson();
                PullRequest pullRequest[] = gson.fromJson(response.body(), PullRequest[].class);
                data.setValue(Arrays.asList(pullRequest));

            }


            @Override
            public void onFailure(final Call<JsonArray> call, final Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

}
