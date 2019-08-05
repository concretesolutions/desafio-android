package com.paulobsa.desafioandroid;

import android.content.Intent;
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
import com.paulobsa.desafioandroid.adapter.RepoListAdapter;
import com.paulobsa.desafioandroid.model.SearchResult;
import com.paulobsa.desafioandroid.util.Util;
import com.paulobsa.desafioandroid.view.PaginationScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.paulobsa.desafioandroid.util.Util.PAGE_START;
import static com.paulobsa.desafioandroid.util.Util.TOTAL_PAGES;


public class RepoListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, RepoListAdapter.RepoListAdapterOnclickHandler {

    @BindView(R.id.repo_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.progressBarRepoList)
    ProgressBar progressBar;

    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private boolean isFirstAttempt = true;

    private RequestQueue queue;

    private RepoListAdapter mRepoListAdapter;
    private LinearLayoutManager mLayoutManager;

    private Gson mGson;
    private SearchResult searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        ButterKnife.bind(this);

        init();

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

        getSupportActionBar().setTitle(getString(R.string.repo_list_title_str));
    }

    private void init() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRepoListAdapter = new RepoListAdapter(this, this);
        mRecyclerView.setAdapter(mRepoListAdapter);

        swipeRefresh.setOnRefreshListener(this);
        mGson = Util.gsonBuilder();
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

        outState.putSerializable(Util.SEARCH_RESULT, mRepoListAdapter.getSearchResult());
        outState.putBoolean(Util.LAST_PAGE, isLastPage);
        outState.putBoolean(Util.LOADING, isLoading);
        outState.putBoolean(Util.FIRST_ATTEMPT, isFirstAttempt);
        outState.putInt(Util.CURRENT_PAGE, currentPage);
    }

    @Override
    public void onCardClick(int position) {
        Intent intent = new Intent(this, PullRequestActivity.class);
        intent.putExtra(Util.USER_NAME, mRepoListAdapter.getSearchResult().getItems().get(position).getOwner().getUserName());
        intent.putExtra(Util.REPO_NAME, mRepoListAdapter.getSearchResult().getItems().get(position).getName());
        startActivity(intent);
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
            mRepoListAdapter.addLoadingFooter();
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

    private void removeLoadingFooter() {
        if (!isFirstAttempt) mRepoListAdapter.removeLoadingFooter();
        isFirstAttempt = false;
    }

    private void setRepoList(SearchResult searchResult) {
        mRepoListAdapter.addSearchResult(searchResult);
    }

    private void showErrorMessage() {
        Toast.makeText(this, "Problema de conexão!", Toast.LENGTH_LONG).show();
    }

    private void fetchRepoInfo(Integer page) {
        queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Util.buildJavaSearch(page).toString(),
                onResponse, onResponseError);
        queue.add(stringRequest);
    }

    private final Response.Listener<String> onResponse = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            removeLoadingFooter();
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

            removeLoadingFooter();
            showErrorMessage();
            isLoading = false;
        }
    };
}
