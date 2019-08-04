package com.paulobsa.desafioandroid;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paulobsa.desafioandroid.model.SearchResult;

public class RepoListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, RepoListAdapter.RepoListAdapterOnclickHandler {

    private RequestQueue queue;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private RepoListAdapter mRepoListAdapter;
    private LinearLayoutManager mLayoutManager;
    private ProgressBar progressBar;
    private Gson mGson;

    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    int itemCount = 0;
    private boolean firstAttempt = true;

    private static final String LOG_TAG = "DESAFIO";
    private static final String url = "https://api.github.com/search/repositories?q=language:Java&sort=stars&page=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);

        mRecyclerView = findViewById(R.id.repo_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRepoListAdapter = new RepoListAdapter(this, this);
        mRecyclerView.setAdapter(mRepoListAdapter);

        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);

        progressBar = findViewById(R.id.progressBarRepoList);
        showProgressBar(true);

        mGson = gsonBuilder();

        // Instantiate the RequestQueue
        queue = Volley.newRequestQueue(this);

        fetchRepoInfo(PAGE_START);

        mRecyclerView.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                mRepoListAdapter.addLoading();
                fetchRepoInfo(currentPage);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    private Gson gsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        return gsonBuilder.create();
    }

    private void setRepoList(SearchResult searchResult) {
        mRepoListAdapter.addSearchResult(searchResult);
    }

    @Override
    public void onCardClick(String repoJson) {
        Toast.makeText(this, "Clicou!", Toast.LENGTH_LONG).show();
    }

    private void showErrorMessage() {
        Toast.makeText(this, "Problema de conexão!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        itemCount = 0;
        isLastPage = false;
        isLoading = true;
        swipeRefresh.setRefreshing(false);
        mRepoListAdapter.clear();
        fetchRepoInfo(PAGE_START);
//        fetchData();
    }

//    private void fetchData() {
//        if (currentPage != PAGE_START) {
//            mRepoListAdapter.removeLoading();
//            fetchRepoInfo(currentPage);
//            swipeRefresh.setRefreshing(false);
//        }
//
//        if (currentPage < totalPage) {
//            mRepoListAdapter.addLoading();
//        } else {
//            isLastPage = true;
//        }
//        isLoading = false;


    private void showProgressBar(boolean show) {
        if (show == true) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void removeLoading() {
        if (!firstAttempt) mRepoListAdapter.removeLoading();
        firstAttempt = false;
    }


    //#####################
    //------NETWORK-------
    private void fetchRepoInfo(Integer page) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + page,
                onResponse, onResponseError);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private final Response.Listener<String> onResponse = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.v(LOG_TAG, response);

            removeLoading();
            SearchResult searchResult = mGson.fromJson(response, SearchResult.class);
            setRepoList(searchResult);
            isLoading = false;
            showProgressBar(false);
        }
    };

    private final Response.ErrorListener onResponseError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            showErrorMessage();
            Log.v(LOG_TAG, error.toString());
            mRepoListAdapter.removeLoading();
            isLoading = false;
        }
    };
}
