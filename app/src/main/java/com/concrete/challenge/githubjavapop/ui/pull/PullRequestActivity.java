package com.concrete.challenge.githubjavapop.ui.pull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.concrete.challenge.githubjavapop.R;

public class PullRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pull_request_activity);

        getSupportActionBar().setTitle(R.string.pull_requests_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PullRequestFragment.newInstance(getIntent().getExtras()), PullRequestFragment.class.getName())
                    .commitNow();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                onBackPressed();
                return super.onOptionsItemSelected(item);
        }

    }
}
