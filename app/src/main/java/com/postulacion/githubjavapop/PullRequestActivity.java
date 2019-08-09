package com.postulacion.githubjavapop;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.postulacion.githubjavapop.adapter.PullRequestRecyclerViewAdapter;
import com.postulacion.githubjavapop.model.PullRequest;
import com.postulacion.githubjavapop.utils.GraphicDetailsKt;
import com.postulacion.githubjavapop.utils.Utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PullRequestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        GraphicDetailsKt.changeColorInitialsViews(this, getWindow());

        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.pulls);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        new PullRequestActivity.GetPullsAsync().execute(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }


    private class GetPullsAsync extends AsyncTask<Context, Void, List<PullRequest>> {

        private String TAG = GetPullsAsync.class.getSimpleName();
        private Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "Initiation background task");
        }

        @Override
        protected List<PullRequest> doInBackground(Context... contexts) {
            context = contexts[0];
            Log.i(TAG, "start async task to get pulls");

            intent = getIntent();
            String pullRequestURL = intent.getStringExtra("pullRequestURL");

            return getPullsRequest(pullRequestURL);
        }

        @Override
        protected void onPostExecute(List<PullRequest> pullRequests) {
            super.onPostExecute(pullRequests);

            if (pullRequests != null) {
                Log.i(TAG, "Task done");

                PullRequestRecyclerViewAdapter pullRequestRecyclerViewAdapter = new PullRequestRecyclerViewAdapter(pullRequests, context);
                recyclerView.setAdapter(pullRequestRecyclerViewAdapter);

                if (pullRequests.size() > 0) {
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                    recyclerView.startAnimation(animation);
                }
            }
        }
    }

    private List<PullRequest> getPullsRequest(String Url) {
        URL url = null;
        try {
            url = new URL(Url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return Utilities.convertJsonToPullRequest(bufferedReader);
        } catch (Exception e) {
            Log.e("ERROR", "Detail: " + e);
        }
        return null;
    }
}
