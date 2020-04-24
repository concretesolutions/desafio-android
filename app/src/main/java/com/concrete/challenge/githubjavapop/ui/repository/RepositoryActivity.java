package com.concrete.challenge.githubjavapop.ui.repository;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.concrete.challenge.githubjavapop.R;

public class RepositoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RepositoryFragment.newInstance())
                    .commitNow();
        }
    }
}
