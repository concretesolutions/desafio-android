package com.example.desafio_concrete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.desafio_concrete.networkUtils.GitHubClient;
import com.example.desafio_concrete.networkUtils.GithubInterface;
import com.example.desafio_concrete.networkUtils.PullRequest;
import com.example.desafio_concrete.utils.EndlessRecyclerViewScrollListener;
import com.example.desafio_concrete.utils.PullRequestAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PullRequestAdapter mAdapter;
    private GithubInterface githubInterface;
    private EndlessRecyclerViewScrollListener scrollListener;
    private String owner;
    private String repoName;
    private ProgressBar mLoadingIndicator;
    private LinearLayoutManager linearLayoutManager;
    private Parcelable mRecyclerViewState;
    private static final String PARCELABLE_KEY = "key";
    private static final String TAG = PullRequestActivity.class.getSimpleName();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        owner = getIntent().getStringExtra("owner");
        repoName = getIntent().getStringExtra("repoName");
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(repoName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_pull_request);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_pull);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        mAdapter = new PullRequestAdapter(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        githubInterface = GitHubClient.getClient().create(GithubInterface.class);

        showLoading();

        loadFirstPage();

    }


    /**
     * Method load the first page in search pull request
     */
    public void loadFirstPage(){

        Call<List<PullRequest>> call = githubInterface.gitPullRequests(owner,repoName);
        Log.d(TAG, call.request().url().toString());

        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                List<PullRequest> list = response.body();
                if(list.size() == 0){
                    Toast.makeText(PullRequestActivity.this, "The search return 0 results"
                    , Toast.LENGTH_LONG).show();
                }
                mAdapter.addPullRequests(list);
                showData();
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                Log.d(TAG, "The call method failed because,  "+t.getMessage());
            }
        });

    }

    /**
     * This method captures the action in menu home
     * @param item the menu in action bar
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showData() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showLoading() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

}
