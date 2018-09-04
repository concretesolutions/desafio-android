package com.example.vladi.consultagit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.vladi.consultagit.R;
import com.example.vladi.consultagit.io.*;
import com.example.vladi.consultagit.io.response.*;
import com.example.vladi.consultagit.models.Repository;
import com.example.vladi.consultagit.ui.adapters.RepositoriesAdapter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoriesActivity extends AppCompatActivity {

    private Boolean scrolling = false;
    private int currentItems, totalItems, scrollOutItems;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<Repository> repositoriesList;
    private RepositoriesAdapter repositoriesAdapter;
    private URI uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        progressBar = (ProgressBar) findViewById(R.id.progress_repo);
        recyclerView = (RecyclerView) findViewById(R.id.repositories_list);
        repositoriesList = new ArrayList<>();
        repositoriesAdapter = new RepositoriesAdapter(repositoriesList, this);

        getQueryParam();

        uri = new URI();
        uri.setQueryParam("language:"+getQueryParam());
        uri.setSort("stars");
        uri.setPage(1);
        getRepositoriesData();
    }

    private String getQueryParam(){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String queryParam;
        if(extras != null){
            queryParam = extras.getString("Query");
        }else{
            queryParam = "";
        }
        return queryParam;
    }

    private void getRepositoriesData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<RepositoriesResponse> call = ApiAdapter.getApiService()
                .getRepositories(uri.getQueryParam(), uri.getSort(), String.valueOf(uri.getPage()));
        call.enqueue(new RepositoriesCallback());
        uri.loadAddPage();
    }

    private void addRepositories(ArrayList<Repository> repositories){
        repositoriesAdapter.notifyItemRangeInserted(repositoriesList.size(), repositories.size());
        repositoriesList.addAll(repositories);
        callRecyclerView(repositoriesList);
        progressBar.setVisibility(View.GONE);
    }

    private void callRecyclerView(final List<Repository> repositories) {

        if(recyclerView.getAdapter() == null){
            repositoriesAdapter = new RepositoriesAdapter(repositories, this);
            linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(repositoriesAdapter);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    scrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = linearLayoutManager.getChildCount();
                totalItems = linearLayoutManager.getItemCount();
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();
                if(scrolling && (currentItems + scrollOutItems == totalItems)){
                    scrolling = false;
                    getRepositoriesData();
                }
            }
        });
    }

    class RepositoriesCallback implements Callback<RepositoriesResponse> {

        @Override
        public void onResponse(Call<RepositoriesResponse> call, Response<RepositoriesResponse> response) {
            if(response.isSuccessful()){
                RepositoriesResponse repositoriesResponse = response.body();
                if(!repositoriesResponse.isIncompleteResults()){
                    addRepositories(repositoriesResponse.getRepositories());
                }
            }else{
                Toast.makeText(getApplicationContext(),R.string.error,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<RepositoriesResponse> call, Throwable t) {
            Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
