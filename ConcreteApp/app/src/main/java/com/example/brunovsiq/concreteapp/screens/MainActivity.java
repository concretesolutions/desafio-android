package com.example.brunovsiq.concreteapp.screens;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidnetworking.AndroidNetworking;
import com.example.brunovsiq.concreteapp.R;
import com.example.brunovsiq.concreteapp.models.Repository;
import com.example.brunovsiq.concreteapp.screens.adapters.RepositoryAdapter;
import com.jacksonandroidnetworking.JacksonParserFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RepositoryAdapter repositoryAdapter;
    // Normally this data should be encapsulated in ViewModels, but shown here for simplicity
    LiveData<PagedList<Repository>> repoList;

    private PagedList<Repository> repositories;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewItems();

        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        getRepos();


    }

    public void getRepos() {
        mainActivityViewModel.getUserPagedList().observe(this, new Observer<PagedList<Repository>>() {
            @Override
            public void onChanged(@Nullable PagedList<Repository> repositoryFromLiveData) {
                repositories = repositoryFromLiveData;
                showOnRecyclerView();
            }
        });
    }

    private void showOnRecyclerView() {
        //RecyclerView recyclerView = activityMainBinding.rvMovies;
        repositoryAdapter = new RepositoryAdapter(this);
        repositoryAdapter.submitList(repositories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(repositoryAdapter);
        repositoryAdapter.notifyDataSetChanged();
//        if (swipeRefreshLayout.isRefreshing()) {
//            swipeRefreshLayout.setRefreshing(false);
//        }
    }

    private void findViewItems() {

        //swipeRefreshLayout = findViewById(R.id.swipe_layout);
        recyclerView = findViewById(R.id.repositories_rv);

    }



}
