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
import com.paulobsa.desafioandroid.util.Util;

public class RepoListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, RepoListAdapter.RepoListAdapterOnclickHandler {

    public static final int PAGE_START = 1;
    private static final int TOTAL_PAGES = 34;

    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private boolean isFirstAttempt = true;

    private RequestQueue queue;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private RepoListAdapter mRepoListAdapter;
    private LinearLayoutManager mLayoutManager;
    private ProgressBar progressBar;
    private Gson mGson;
    private SearchResult searchResult;

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

        mGson = gsonBuilder();

        // Instantiate the RequestQueue
        queue = Volley.newRequestQueue(this);

        if (savedInstanceState == null) {
            fetchData();
        } else {
            loadState(savedInstanceState);
        }

        mRecyclerView.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                fetchData();
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

    private void loadState(Bundle savedInstanceState) {
        setRepoList((SearchResult) savedInstanceState.getSerializable(Util.SEARCH_RESULT));
        isLastPage = savedInstanceState.getBoolean(Util.LAST_PAGE, isLastPage);
        isLoading = savedInstanceState.getBoolean(Util.LOADING, isLoading);
        isFirstAttempt = savedInstanceState.getBoolean(Util.FIRST_ATTEMPT, isFirstAttempt);
        currentPage = savedInstanceState.getInt(Util.CURRENT_PAGE, currentPage);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(Util.SEARCH_RESULT, searchResult);
        outState.putBoolean(Util.LAST_PAGE, isLastPage);
        outState.putBoolean(Util.LOADING, isLoading);
        outState.putBoolean(Util.FIRST_ATTEMPT, isFirstAttempt);
        outState.putInt(Util.CURRENT_PAGE, currentPage);
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
        isLastPage = false;
        isLoading = true;
        swipeRefresh.setRefreshing(false);
        mRepoListAdapter.clear();
        currentPage = PAGE_START;
        fetchData();
    }

    private void fetchData() {
        if (currentPage == PAGE_START) {
            enableProgressBar();
            fetchRepoInfo(PAGE_START);
        } else {
            isLoading = true;
            mRepoListAdapter.addLoading();
            fetchRepoInfo(currentPage);
        }

        if (currentPage >= TOTAL_PAGES) isLastPage = true;
    }

    private void disableProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void enableProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void removeLoading() {
        if (!isFirstAttempt) mRepoListAdapter.removeLoading();
        isFirstAttempt = false;
    }

    //#####################
    //------NETWORK-------
    private void fetchRepoInfo(Integer page) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Util.buildJavaSearch(page).toString(),
                onResponse, onResponseError);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private final Response.Listener<String> onResponse = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            removeLoading();
            searchResult = mGson.fromJson(response, SearchResult.class);
            setRepoList(searchResult);
            isLoading = false;
            currentPage++;
            disableProgressBar();
        }
    };

    private final Response.ErrorListener onResponseError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.v(Util.LOG_TAG, error.toString());

            removeLoading();
            showErrorMessage();
            isLoading = false;
        }
    };
}
