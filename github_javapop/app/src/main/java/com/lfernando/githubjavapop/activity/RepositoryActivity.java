package com.lfernando.githubjavapop.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lfernando.githubjavapop.R;
import com.lfernando.githubjavapop.adapter.PullRequestAdapter;
import com.lfernando.githubjavapop.constants.Constants;
import com.lfernando.githubjavapop.model.PullRequest;
import com.lfernando.githubjavapop.network.GithubApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryActivity extends AppCompatActivity {

    TextView prOpened, prClosed;
    RecyclerView pullRequestsRV;
    ProgressBar prLoadingPB;

    List<PullRequest> pullRequestList;

    Retrofit retrofit;
    GithubApi service;
    PullRequestAdapter adapter;

    Intent intent;

    private String owner;
    private String repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_list);

        setup();
        loadData();

        if (getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    protected void setup() {
        prOpened = findViewById(R.id.prOpenedTV);
        prClosed = findViewById(R.id.prClosedTV);
        pullRequestsRV = findViewById(R.id.prListRV);
        prLoadingPB = findViewById(R.id.prLoadingPB);

        pullRequestList = new ArrayList<PullRequest>();
        adapter = new PullRequestAdapter(pullRequestList, this);
        pullRequestsRV.setAdapter(adapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        pullRequestsRV.setLayoutManager(layout);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(GithubApi.class);

        intent = getIntent();
        owner = intent.getStringExtra("owner");
        repository = intent.getStringExtra("repository");

        this.setTitle(repository);
    }

    protected void loadData() {
        prLoadingPB.setVisibility(View.VISIBLE);
        service.listPrs(owner, repository).enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pullRequestList.addAll(response.body());
                    RepositoryActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            prLoadingPB.setVisibility(View.GONE);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
