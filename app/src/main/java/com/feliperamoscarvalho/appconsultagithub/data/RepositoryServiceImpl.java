package com.feliperamoscarvalho.appconsultagithub.data;

import com.feliperamoscarvalho.appconsultagithub.data.model.RepositorySearchResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that implements the RepositoryServiceAPI interface. Is responsible for searching repositories
 * at the endpoint
 */
public class RepositoryServiceImpl implements RepositoryServiceAPI {

    RetrofitEndpoint mRetrofit;

    private final String PARAM_Q_KEY = "q";
    private final String PARAM_Q_VALUE = "language:Java";
    private final String PARAM_SORT_KEY = "sort";
    private final String PARAM_SORT_VALUE = "stars";
    private final String PARAM_PAGE_KEY = "page";

    /**
     * Constructor starts and populates the retrofit object
     */
    public RepositoryServiceImpl() {
        mRetrofit = RetrofitClient.getClient().create(RetrofitEndpoint.class);
    }

    /**
     * Create the query map that will be used to filter the endpoint search.
     *
     * @param pageNumber Page number to search for endpoint
     */
    private HashMap<String, String> getQueryMap(String pageNumber) {

        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put(PARAM_Q_KEY, PARAM_Q_VALUE);
        queryMap.put(PARAM_SORT_KEY, PARAM_SORT_VALUE);
        queryMap.put(PARAM_PAGE_KEY, String.valueOf(pageNumber));

        return queryMap;

    }

    /**
     * Implements the interface method to fetch repository data from endpoint
     * The search is done using the Retrofit object.
     *
     * @param callback callback that will be executed after loading all endpoint data.
     */
    @Override
    public void getRepositories(final RepositoryServiceCallback<RepositorySearchResult> callback) {
        Call<RepositorySearchResult> callRepositories = mRetrofit.getRepositories(getQueryMap("1"));
        callRepositories.enqueue(new Callback<RepositorySearchResult>() {
            @Override
            public void onResponse(Call<RepositorySearchResult> call, Response<RepositorySearchResult> response) {
                if (response.code() == 200) {
                    RepositorySearchResult repositorySearchResult = response.body();
                    callback.onLoaded(repositorySearchResult);
                }
            }

            @Override
            public void onFailure(Call<RepositorySearchResult> call, Throwable t) {

            }
        });
    }
}
