package com.lfernando.githubjavapop.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lfernando.githubjavapop.R;
import com.lfernando.githubjavapop.adapter.RepositoryAdapter;
import com.lfernando.githubjavapop.constants.Constants;
import com.lfernando.githubjavapop.model.Repo;
import com.lfernando.githubjavapop.network.GithubApi;
import com.lfernando.githubjavapop.network.RepositoryReponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView repositoryRV;
    ProgressBar loadingPB;

    List<Repo> repoList;
    Retrofit retrofit;
    GithubApi service;
    RepositoryAdapter adapter;
    Cache cache;

    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        loadData();

        repositoryRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    loadData();

                }
            }
        });

    }

    protected void setup() {
        repoList = new ArrayList<Repo>();

        adapter = new RepositoryAdapter(repoList, this);
        repositoryRV = findViewById(R.id.repositoryRV);
        repositoryRV.setAdapter(adapter);

        loadingPB = findViewById(R.id.loadingPB);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(repositoryRV.getContext(),
                DividerItemDecoration.VERTICAL);

        repositoryRV.addItemDecoration(mDividerItemDecoration);
        repositoryRV.setLayoutManager(layout);


        cache = new Cache(getCacheDir(), Constants.cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        service = retrofit.create(GithubApi.class);
    }

    protected void loadData() {
        currentPage++;
        loadingPB.setVisibility(View.VISIBLE);
        service.listRepos(String.valueOf(currentPage)).enqueue(new Callback<RepositoryReponse>() {
            @Override
            public void onResponse(Call<RepositoryReponse> call, Response<RepositoryReponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    repoList.addAll(response.body().getItems());
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            loadingPB.setVisibility(View.GONE);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<RepositoryReponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
