package com.paulobsa.desafioandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.paulobsa.desafioandroid.adapter.PullRequestListAdapter;
import com.paulobsa.desafioandroid.model.PullRequest;
import com.paulobsa.desafioandroid.model.PullRequestSearchResult;
import com.paulobsa.desafioandroid.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullRequestActivity extends AppCompatActivity implements PullRequestListAdapter.PullRequestListAdapterOnclickHandler{
    @BindView(R.id.pull_request_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBarPRList)
    ProgressBar progressBar;

    private RequestQueue queue;

    private PullRequestListAdapter mPullRequestListAdapter;
    private LinearLayoutManager mLayoutManager;

    private Gson mGson;
    private PullRequestSearchResult pullRequestSearchResult;
    private String userName;
    private String repoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request_list);
        ButterKnife.bind(this);

        init();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userName = bundle.getString(Util.USER_NAME);
            repoName = bundle.getString(Util.REPO_NAME);
        }

        if (savedInstanceState == null) {
            fetchPullRequests();
        } else {
            loadState(savedInstanceState);
        }

        getSupportActionBar().setTitle(getString(R.string.pull_requests_title_str));
    }

    private void init() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mPullRequestListAdapter = new PullRequestListAdapter(this, this);
        mRecyclerView.setAdapter(mPullRequestListAdapter);
        mGson = Util.gsonBuilder();
    }

    @Override
    public void onCardClick(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void loadState(Bundle savedInstanceState) {
        setPullRequestList((PullRequestSearchResult) savedInstanceState.getSerializable(Util.PULL_REQUESTS));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(Util.PULL_REQUESTS, mPullRequestListAdapter.getSearchResult());
    }

    private void showErrorMessage() {
        Toast.makeText(this, "Problema de conexão!", Toast.LENGTH_LONG).show();
    }

    private void fetchPullRequests() {
        queue = Volley.newRequestQueue(this);
        enableProgressBar();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Util.buildPullRequestSearch(userName, repoName).toString(),
                onResponse, onResponseError);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private final Response.Listener<String> onResponse = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            pullRequestSearchResult = new PullRequestSearchResult();
            pullRequestSearchResult.setPullRequests(mGson.fromJson(response, PullRequest[].class));
            setPullRequestList(pullRequestSearchResult);
            disableProgressBar();
        }
    };

    private final Response.ErrorListener onResponseError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.v(Util.LOG_TAG, error.toString());
            showErrorMessage();
            disableProgressBar();
        }
    };

    private void setPullRequestList(PullRequestSearchResult pullRequestSearchResult) {
        mPullRequestListAdapter.addResult(pullRequestSearchResult);
    }

    private void disableProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void enableProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
