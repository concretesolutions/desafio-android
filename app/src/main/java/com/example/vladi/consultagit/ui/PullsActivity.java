package com.example.vladi.consultagit.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vladi.consultagit.R;
import com.example.vladi.consultagit.io.ApiAdapter;
import com.example.vladi.consultagit.io.URI;
import com.example.vladi.consultagit.io.response.PullsResponse;
import com.example.vladi.consultagit.models.PullRequest;
import com.example.vladi.consultagit.ui.adapters.PullRequestsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullsActivity extends AppCompatActivity {

    private Boolean scrolling = false;
    private int currentItems, totalItems, scrollOutItems;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<PullRequest> pullRequestList;
    private PullRequestsAdapter pullRequestsAdapter;
    private URI uri;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulls);

        progressBar = (ProgressBar) findViewById(R.id.progress_repo);
        recyclerView = (RecyclerView) findViewById(R.id.pull_requests_list);
        pullRequestList = new ArrayList<>();
        pullRequestsAdapter = new PullRequestsAdapter(pullRequestList, this);

        getQueryParam();

        uri = new URI();
        uri.setPathParam1(getQueryParam()[0]);
        uri.setPathParam2(getQueryParam()[1]);

        //getRepositoriesData();

        // Ejemplo de acceso para ver un Pull Request en específico (Activity 4 - PullRequestActivity)
        button = (Button) findViewById(R.id.example_pull);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PullsActivity.this, PullRequestActivity.class);
                startActivity(intent);
            }
        });
    }

    private String[] getQueryParam(){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String queryParam[] = new String[2];
        if(extras != null){
            queryParam[0] = extras.getString("Owner");
            queryParam[1] = extras.getString("Name");
        }else{
            queryParam[0] = "";
            queryParam[1] = "";
        }
        return queryParam;
    }

    private void getRepositoriesData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<PullsResponse> call = ApiAdapter.getApiService()
                .getPulls(uri.getPathParam1(),uri.getPathParam2());
        call.enqueue(new PullsCallback());
    }

    private void addPullRequests(ArrayList<PullRequest> pullRequests){
        pullRequestsAdapter.notifyItemRangeInserted(pullRequestList.size(), pullRequests.size());
        pullRequestList.addAll(pullRequests);
        callRecyclerView(pullRequestList);
        progressBar.setVisibility(View.GONE);
    }

    private void callRecyclerView(final List<PullRequest> pullRequests) {

        if(recyclerView.getAdapter() == null){
            pullRequestsAdapter = new PullRequestsAdapter(pullRequests,this);
            linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(pullRequestsAdapter);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    scrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = linearLayoutManager.getChildCount();
                totalItems = linearLayoutManager.getItemCount();
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();
                if(scrolling && (currentItems + scrollOutItems == totalItems)){
                    scrolling = false;
                    //getRepositoriesData();
                }
            }
        });
    }

    class PullsCallback implements Callback<PullsResponse> {

        @Override
        public void onResponse(Call<PullsResponse> call, Response<PullsResponse> response) {
            if(response.isSuccessful()){
                PullsResponse pullsResponse = response.body();
                if(pullsResponse != null){
                    addPullRequests(pullsResponse.getPulls());
                }
            }else{
                Toast.makeText(getApplicationContext(),R.string.error,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<PullsResponse> call, Throwable t) {

        }
    }

}
