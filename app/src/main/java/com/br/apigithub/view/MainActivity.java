package com.br.apigithub.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.br.apigithub.R;
import com.br.apigithub.aac.RepositoryViewModel;
import com.br.apigithub.beans.Pull;

import java.util.List;

/**
 * Created by rlima on 04/10/18.
 */

public class MainActivity extends AppCompatActivity implements RepositoryAdapter.ItemClickListener {
    public static final int INITIAL_PAGE = 1;
    private RepositoryViewModel repoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repoViewModel = ViewModelProviders.of(this).get(RepositoryViewModel.class);
        repoViewModel.getPullsLiveData().observe(this, new Observer<List<Pull>>() {
            @Override
            public void onChanged(@Nullable List<Pull> pulls) {
                PullRequestFragment fragment = (PullRequestFragment) getSupportFragmentManager().findFragmentByTag(PullRequestFragment.TAG);
                if (pulls == null || pulls.isEmpty()) {
                    fragment.showEmptyLayout();
                } else {
                    fragment.showPullRequests(pulls);
                }
            }
        });
        if (savedInstanceState == null) {
            repoViewModel.listRepos(INITIAL_PAGE);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            RepositoryFragment fragment = RepositoryFragment.newInstance();
            transaction.add(R.id.fragment_container, fragment, RepositoryFragment.TAG).commit();
        }
    }

    @Override
    public void onItemClick(String ownerRepo, final String repoName) {
        repoViewModel.listPullRequests(ownerRepo, repoName, INITIAL_PAGE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        PullRequestFragment fragment = PullRequestFragment.newInstance();

        transaction.replace(R.id.fragment_container, fragment, PullRequestFragment.TAG).addToBackStack(PullRequestFragment.TAG).commit();
    }

}
