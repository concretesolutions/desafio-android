package com.example.github_api_concrete.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.github_api_concrete.R;
import com.example.github_api_concrete.model.pojo.repos.Item;
import com.example.github_api_concrete.view.adapter.ReposRecyclerViewAdapter;
import com.example.github_api_concrete.view.interfaces.OnClick;
import com.example.github_api_concrete.viewmodel.ReposViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class ReposActivity extends AppCompatActivity implements OnClick {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ReposViewModel viewModel;
    private List<Item> itemList = new ArrayList<>();
    private ReposRecyclerViewAdapter adapter;
    private String language = "language:Java";
    private String sort = "stars";
    private int page = 1;
    public static final String OWNER = "owner";
    public static final String REPO = "repo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        initViews();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setScrollView();

        viewModel.getAllItems(language, sort, page);
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

    private void setScrollView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                boolean lastItem = lastVisible + 5 >= totalItemCount;

                if (totalItemCount > 0 && lastItem) {
                    page++;
                    viewModel.getAllItems(language, sort, page);
                }
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        viewModel = ViewModelProviders.of(this).get(ReposViewModel.class);
        adapter = new ReposRecyclerViewAdapter(itemList, this);
    }

    @Override
    public void click(Item item) {
        Intent intent = new Intent(ReposActivity.this, PullRequestsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(OWNER, item.getOwner().getLogin());
        bundle.putString(REPO, item.getName());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
