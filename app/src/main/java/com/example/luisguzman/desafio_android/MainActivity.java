package com.example.luisguzman.desafio_android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.luisguzman.desafio_android.adapter.ApiAdapter;
import com.example.luisguzman.desafio_android.adapter.RepoAdapter;
import com.example.luisguzman.desafio_android.modal.DataList;
import com.example.luisguzman.desafio_android.modal.RepoList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Boolean scrolling = false;
    private int currentItems, totalItems, scrollOutItems;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<RepoList> repoList;
    private RepoAdapter repoAdapter;
    private Spinner languages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_repo);
        languages = findViewById(R.id.languages);

        recyclerView = findViewById(R.id.repositories_list);
        repoList = new ArrayList<>();
        repoAdapter = new RepoAdapter(repoList, this);

        languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getRepositoriesData(languages.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getRepositoriesData(languages.getSelectedItem().toString());

    }

    private void getRepositoriesData(String lang) {
        System.out.println(lang);
        progressBar.setVisibility(View.VISIBLE);
        Call<DataList> call = ApiAdapter.getApiService()
                .getRepos(lang, "stars", "1");
        call.enqueue(new Callback<DataList>() {
            @Override
            public void onResponse(@NonNull Call<DataList> call, @NonNull Response<DataList> response) {
                if (response.isSuccessful()) {
                    DataList reposResponse = response.body();
                    if (!reposResponse.isIncompleteResults()) {
                        addRepositories(reposResponse.getRepos());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataList> call, @NonNull Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
                Toast.makeText(getApplicationContext(), R.string.error_toast, Toast.LENGTH_LONG).show();

            }
        });
    }

    private void addRepositories(ArrayList<RepoList> repos) {
        repoList.clear();
        repoAdapter.notifyItemRangeInserted(repoList.size(), repos.size());
        repoList.addAll(repos);
        callRecyclerView(repoList);
        progressBar.setVisibility(View.GONE);
        repoAdapter.notifyDataSetChanged();
    }

    private void callRecyclerView(final List<RepoList> repos) {

        if (recyclerView.getAdapter() == null) {
            repoAdapter = new RepoAdapter(repos, this);
            linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(repoAdapter);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    scrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = linearLayoutManager.getChildCount();
                totalItems = linearLayoutManager.getItemCount();
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();
                if (scrolling && (currentItems + scrollOutItems == totalItems)) {
                    scrolling = false;
                    getRepositoriesData(languages.getSelectedItem().toString());
                }
            }
        });
    }
}
