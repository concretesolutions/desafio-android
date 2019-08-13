package com.desafioconcret;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.desafioconcret.adapters.PullAdapter;
import com.desafioconcret.pojo.json.PullRequests;

import java.util.List;

public class PullRequestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PullAdapter adapter;

    private List<PullRequests> pullRequestses;

    private String creator;
    private String repositories;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.pulls_recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SimpleDivider(this));

        Bundle bundle = getIntent().getExtras();
        creator = bundle.getString("OWNER");
        repositories = bundle.getString("REPO");

        toolbar = (Toolbar) findViewById(R.id.toolbar_pullrequest);
        toolbar.setNavigationIcon(R.drawable.ic_action_goleft);
        toolbar.setTitle(repositories.toString());
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


    }
}
