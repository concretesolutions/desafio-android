package com.example.github_api_concrete.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.github_api_concrete.R;
import com.example.github_api_concrete.model.pojo.repos.Item;
import com.example.github_api_concrete.view.adapter.PullRequestsRecyclerViewAdapter;
import com.example.github_api_concrete.view.adapter.ReposRecyclerViewAdapter;
import com.example.github_api_concrete.view.interfaces.OnClick;
import com.example.github_api_concrete.viewmodel.PullRequestsViewModel;
import com.example.github_api_concrete.viewmodel.ReposViewModel;

import java.util.ArrayList;
import java.util.List;

public class PullRequestsActivity extends AppCompatActivity implements OnClick {

    private RecyclerView recyclerViewPR;
    private ProgressBar progressBarPR;
    private PullRequestsViewModel viewModelPR;
    private PullRequestsRecyclerViewAdapter adapterPR;
    private List<Item> pullRequestList = new ArrayList<>();
    private String login;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_requests);

        initViews();

        if (getIntent() != null) {
            Item item = getIntent().getParcelableExtra("Item");
            login = item.getOwner().getLogin();
            name = item.getName();

            recyclerViewPR.setAdapter(adapterPR);
            recyclerViewPR.setLayoutManager(new LinearLayoutManager(this));

            viewModelPR.getAllPR(login, name);
            viewModelPR.getListPR().observe(this, pullRequests -> {
                adapterPR.updateList(pullRequests);
            });
            viewModelPR.getLoading().observe(this, loading -> {
                if (loading) {
                    progressBarPR.setVisibility(View.VISIBLE);
                } else {
                    progressBarPR.setVisibility(View.GONE);
                }
            });

        }
    }

    private void initViews() {
        recyclerViewPR = findViewById(R.id.recyclerViewPR);
        progressBarPR = findViewById(R.id.progressBarPR);
        viewModelPR = ViewModelProviders.of(this).get(PullRequestsViewModel.class);
        adapterPR = new PullRequestsRecyclerViewAdapter(pullRequestList, this);
    }

    @Override
    public void click(Item item) {

    }
}
