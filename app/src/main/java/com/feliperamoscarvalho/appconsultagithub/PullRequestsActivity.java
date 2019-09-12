package com.feliperamoscarvalho.appconsultagithub;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.feliperamoscarvalho.appconsultagithub.pullrequests.PullRequestsFragment;

public class PullRequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_requests);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String repositoryName = bundle.getString("repositoryName").toString();
        getSupportActionBar().setTitle(repositoryName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            addFragment(PullRequestsFragment.newInstance(), bundle);
        }
    }

    /**
     * Add fragment to Frame Layout.
     * The bundle with the parameters that will be used in the endpoint search is sent to the fragment.
     */
    private void addFragment(Fragment pullRequestsFragment, Bundle args) {
        pullRequestsFragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, pullRequestsFragment);
        fragmentTransaction.commit();
    }
}
