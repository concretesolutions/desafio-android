package com.lfernando.githubjavapop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lfernando.githubjavapop.R;
import com.lfernando.githubjavapop.adapter.RepositoryAdapter;
import com.lfernando.githubjavapop.constants.Constants;
import com.lfernando.githubjavapop.model.Repo;
import com.lfernando.githubjavapop.network.GithubApi;
import com.lfernando.githubjavapop.network.RepositoryReponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView repositoryRV;

    List<Repo> repoList;
    Retrofit retrofit;
    GithubApi service;
    RepositoryAdapter adapter;

    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        loadData();
    }

    protected void setup() {
        repoList = new ArrayList<Repo>();

        adapter = new RepositoryAdapter(repoList, this);
        repositoryRV = findViewById(R.id.repositoryRV);
        repositoryRV.setAdapter(adapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        repositoryRV.setLayoutManager(layout);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(GithubApi.class);
    }

    protected void loadData() {
        currentPage++;
        service.listRepos(String.valueOf(currentPage)).enqueue(new Callback<RepositoryReponse>() {
            @Override
            public void onResponse(Call<RepositoryReponse> call, Response<RepositoryReponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    repoList.addAll(response.body().getItems());
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
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
