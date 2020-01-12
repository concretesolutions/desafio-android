package com.example.github_api_concrete.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.github_api_concrete.R;
import com.example.github_api_concrete.model.pojo.pullrequests.Response;
import com.example.github_api_concrete.model.pojo.repos.Item;
import com.example.github_api_concrete.view.adapter.PullRequestsRecyclerViewAdapter;
import com.example.github_api_concrete.view.adapter.ReposRecyclerViewAdapter;
import com.example.github_api_concrete.view.interfaces.OnClick;
import com.example.github_api_concrete.view.interfaces.OnClickPR;
import com.example.github_api_concrete.viewmodel.PullRequestsViewModel;
import com.example.github_api_concrete.viewmodel.ReposViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static com.example.github_api_concrete.view.activities.ReposActivity.OWNER;
import static com.example.github_api_concrete.view.activities.ReposActivity.REPO;

public class PullRequestsActivity extends AppCompatActivity implements OnClickPR {

    private RecyclerView recyclerViewPR;
    private ProgressBar progressBarPR;
    private PullRequestsViewModel viewModelPR;
    private PullRequestsRecyclerViewAdapter adapterPR;
    private List<Response> pullRequestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_requests);

        initViews();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String owner = bundle.getString(OWNER);
        String repo = bundle.getString(REPO);

        recyclerViewPR.setAdapter(adapterPR);
        recyclerViewPR.setLayoutManager(new LinearLayoutManager(this));

        viewModelPR.getAllPR(owner, repo);

        viewModelPR.getListPR().observe(this, pullRequests -> {
            if (pullRequests != null && !pullRequests.isEmpty()){
                adapterPR.updateList(pullRequests);
            } else {
                Snackbar.make(recyclerViewPR, "Não existem pull requests listados neste repositório.", Snackbar.LENGTH_LONG).show();
            }
        });

        viewModelPR.getLoading().observe(this, loading -> {
            if (loading) {
                progressBarPR.setVisibility(View.VISIBLE);
            } else {
                progressBarPR.setVisibility(View.GONE);
            }
        });

    }

    private void initViews() {
        recyclerViewPR = findViewById(R.id.recyclerViewPR);
        progressBarPR = findViewById(R.id.progressBarPR);
        viewModelPR = ViewModelProviders.of(this).get(PullRequestsViewModel.class);
        adapterPR = new PullRequestsRecyclerViewAdapter(pullRequestList, this);
    }

    @Override
    public void click(Response response) {
        String pullRequestPage = response.getHtmlUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(pullRequestPage));
        startActivity(intent);
    }
}
