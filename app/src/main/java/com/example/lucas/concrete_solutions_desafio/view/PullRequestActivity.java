package com.example.lucas.concrete_solutions_desafio.view;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.example.lucas.concrete_solutions_desafio.R;
import com.example.lucas.concrete_solutions_desafio.contract.PullRequestContract;
import com.example.lucas.concrete_solutions_desafio.model.PullRequest;
import com.example.lucas.concrete_solutions_desafio.model.PullRequestList;
import com.example.lucas.concrete_solutions_desafio.model.User;
import com.example.lucas.concrete_solutions_desafio.presenter.PullRequestPresenter;
import com.example.lucas.concrete_solutions_desafio.view.adapter.PullRequestAdapter;

public class PullRequestActivity extends AppCompatActivity implements PullRequestContract.View{

    private PullRequestPresenter presenter = new PullRequestPresenter(this);
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pull_request);
        Intent intentObject = getIntent();
        Bundle bundle= intentObject.getExtras();
        int colorDetails = getResources().getColor(bundle.getInt("color_details"));
        String name = bundle.getString("name");

        getSupportActionBar().setTitle(name);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(colorDetails));
        progressBar = findViewById(R.id.pullRequest_pbProgressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(colorDetails, android.graphics.PorterDuff.Mode.MULTIPLY);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        recyclerView = findViewById(R.id.pullRequest_rvPullRequestList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        presenter.pullRequestRequisition( bundle.getString("full_name"), getBaseContext());
    }

    @Override
    public void fillScreen(PullRequestList pullRequests) {
        final int empty = 0;

        if(pullRequests.pullRequestsCount()!=empty){
            RecyclerView.Adapter adapter = new PullRequestAdapter(pullRequests, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
        else {
            User user = new User("","");
            PullRequest pullRequest = new PullRequest("Está vazio :)", "Não há pullrequests para este repositório.", null, user);
            pullRequests.setPullRequests(pullRequest);
            RecyclerView.Adapter adapter = new PullRequestAdapter(pullRequests, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void interruptProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
