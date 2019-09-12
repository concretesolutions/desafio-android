package com.feliperamoscarvalho.appconsultagithub.data;

import com.feliperamoscarvalho.appconsultagithub.data.model.Pull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that implements the PullRequestServiceAPI interface. Is responsible for searching pull requests
 * at the endpoint
 */
public class PullRequestServiceImpl implements PullRequestServiceAPI {

    RetrofitEndpoint mRetrofit;

    /**
     * Constructor starts and populates the retrofit object
     */
    public PullRequestServiceImpl() {
        mRetrofit = RetrofitClient.getClient().create(RetrofitEndpoint.class);
    }

    /**
     * Implements the interface method to fetch pul requests data from endpoint.
     * The search is done using the Retrofit object.
     *
     * @param login      login that will be used in the pull requests search.
     * @param repository name of the repository that will be used in the pull requests search.
     * @param callback   callback that will be executed after loading all endpoint data.
     */
    @Override
    public void getPullRequests(String login, String repository,
                                final PullRequestServiceCallback<List<Pull>> callback) {
        Call<List<Pull>> callPullRequests = mRetrofit.getPullRequests(login, repository);
        callPullRequests.enqueue(new Callback<List<Pull>>() {
            @Override
            public void onResponse(Call<List<Pull>> call, Response<List<Pull>> response) {
                if (response.code() == 200) {
                    List<Pull> listPullRequests = response.body();
                    callback.onLoaded(listPullRequests);
                }
            }

            @Override
            public void onFailure(Call<List<Pull>> call, Throwable t) {

            }
        });
    }
}
