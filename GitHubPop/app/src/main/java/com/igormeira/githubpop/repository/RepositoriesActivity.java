package com.igormeira.githubpop.repository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.igormeira.githubpop.R;
import com.igormeira.githubpop.databinding.ActivityRepositoriesBinding;
import com.igormeira.githubpop.model.Repository;

import java.util.ArrayList;

import static com.igormeira.githubpop.util.Dialog.showLoadingDialog;

public class RepositoriesActivity extends AppCompatActivity {

    private RepositoriesViewModel repositoriesViewModel;
    private RepositoriesRecyclerAdapter repositoriesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRepositoriesBinding activityRepositoriesBinding =
                DataBindingUtil.setContentView(this,R.layout.activity_repositories);

        // Bind RecyclerView
        RecyclerView recyclerView = activityRepositoriesBinding.recyclerViewRepository;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        repositoriesViewModel = ViewModelProviders.of(this).get(RepositoriesViewModel.class);
        repositoriesRecyclerAdapter = new RepositoriesRecyclerAdapter();
        recyclerView.setAdapter(repositoriesRecyclerAdapter);
        getAllRepositories();
        repositoriesRecyclerAdapter.setContext(this);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                repositoriesViewModel.addDataToList();
            }
        });
    }

    private void getAllRepositories() {
        ProgressDialog dialog = showLoadingDialog(this);
        dialog.show();
        repositoriesViewModel.getAllRepositories().observe(this, repositories -> {
            dialog.dismiss();
            repositoriesRecyclerAdapter.setRepositoryList((ArrayList<Repository>) repositories);
        });
    }
}
