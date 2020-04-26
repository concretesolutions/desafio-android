package com.concrete.challenge.githubjavapop.ui.pull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.concrete.challenge.githubjavapop.R;

public class PullRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pull_request_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PullRequestFragment.newInstance(), PullRequestFragment.class.getName())
                    .commitNow();
        }
    }
}
