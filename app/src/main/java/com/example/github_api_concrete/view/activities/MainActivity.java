package com.example.github_api_concrete.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.github_api_concrete.R;
import com.example.github_api_concrete.model.pojo.Item;
import com.example.github_api_concrete.view.adapter.GitHubRecyclerViewAdapter;
import com.example.github_api_concrete.view.interfaces.OnClick;
import com.example.github_api_concrete.viewmodel.GitHubViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClick {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private GitHubViewModel viewModel;
    private List<Item> itemList = new ArrayList<>();
    private GitHubRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getAllItems();
        viewModel.getListItem().observe(this, items -> {
            adapter.updateList(items);
        });
        viewModel.getLoading().observe(this, loading -> {
            if (loading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        viewModel = ViewModelProviders.of(this).get(GitHubViewModel.class);
        adapter = new GitHubRecyclerViewAdapter(itemList, this);
    }

    @Override
    public void click(Item item) {

    }
}
