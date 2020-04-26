package com.concrete.challenge.githubjavapop.ui.pull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.concrete.challenge.githubjavapop.R;

public class PullRequestActivity extends AppCompatActivity {

    public static String USER_NAME_KEY = "__user_name_key__";
    public static String REPOSITORY_NAME_KEY = "__repository_name_key__";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pull_request_activity);

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
