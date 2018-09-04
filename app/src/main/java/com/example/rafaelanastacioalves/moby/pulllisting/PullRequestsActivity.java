package com.example.rafaelanastacioalves.moby.pulllisting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.rafaelanastacioalves.moby.R;

import timber.log.Timber;


public class PullRequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);
        setupActionBar();


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Timber.i("PullRequestsFragment ARG PACKAGE: " + getIntent().getStringExtra(PullRequestsFragment.ARG_PACKAGE_ID));
            Bundle arguments = new Bundle();
            arguments.putString(PullRequestsFragment.ARG_CREATOR,
                    getIntent().getStringExtra(PullRequestsFragment.ARG_CREATOR));

            Timber.i("PullRequestsFragment ARG REPOSITORY: " + getIntent().getStringExtra(PullRequestsFragment.ARG_REPOSITORY));
            arguments.putString(PullRequestsFragment.ARG_REPOSITORY,
                    getIntent().getStringExtra(PullRequestsFragment.ARG_REPOSITORY));
            PullRequestsFragment fragment = new PullRequestsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.package_detail_fragment_container, fragment)
                    .commit();
        }
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

    }

}
