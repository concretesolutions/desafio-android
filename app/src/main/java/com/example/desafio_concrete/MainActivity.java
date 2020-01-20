package com.example.desafio_concrete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.desafio_concrete.networkUtils.Item;
import com.example.desafio_concrete.networkUtils.Repository;
import com.example.desafio_concrete.networkUtils.GitHubClient;
import com.example.desafio_concrete.networkUtils.GithubInterface;
import com.example.desafio_concrete.utils.EndlessRecyclerViewScrollListener;
import com.example.desafio_concrete.utils.RepositoryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements
        RepositoryAdapter.RepositoryAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private RepositoryAdapter mAdapter;
    private GithubInterface githubInterface;
    private static int PAGE = 1;
    private EndlessRecyclerViewScrollListener scrollListener;
    private ProgressBar mLoadingIndicator;
    private LinearLayoutManager linearLayoutManager;
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_repository);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new RepositoryAdapter(this, this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL));

        mRecyclerView.setSaveEnabled(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        githubInterface = GitHubClient.getClient().create(GithubInterface.class);


        showLoading();


        loadFirstPage();


        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                nextPag();
            }

        };

        mRecyclerView.addOnScrollListener(scrollListener);

    }


    /**
     * The method load the first page of Java List Repository
     */
    public void loadFirstPage(){
        Call<Repository> call = githubInterface.gitRepositories(PAGE);

        call.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                List<Item> repositories = response.body().getItems();
                mAdapter.addRepositories(repositories);
                showData();
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Log.d(TAG, "The search returns "+t.getMessage());

            }
        });
    }

    /**
     * The method search the next page in repository java
     */
    private void nextPag(){
        PAGE++;
        Log.d(TAG, "Actual Number Page: "+PAGE);
        Call<Repository> call = githubInterface.gitRepositories(PAGE);
        call.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                try{
                    List<Item> reposiItems = response.body().getItems();
                    mAdapter.addRepositories(reposiItems);

                    }catch (NullPointerException e){
                    Toast.makeText(MainActivity.this, "The List ended",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Log.d(TAG, "The search returns 0 results, "+t.getMessage());
            }
        });

    }


    @Override
    public void onClick(int data) {
        Intent intent = new Intent(MainActivity.this, PullRequestActivity.class);
        String name = mAdapter.getItem(data).getName();
        String userName = mAdapter.getItem(data).getOwner().getNameAuthor();
        intent.putExtra("owner", userName);
        intent.putExtra("repoName", name);
        startActivity(intent);
    }


    /**
     * This method show data after search in activity
     */
    private void showData() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * This method show the loading indicator before data load
     */
    private void showLoading() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }


}


