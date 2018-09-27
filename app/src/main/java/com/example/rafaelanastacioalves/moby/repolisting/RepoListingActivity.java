package com.example.rafaelanastacioalves.moby.repolisting;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.vo.Repo;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;
import com.example.rafaelanastacioalves.moby.pulllisting.PullRequestsActivity;
import com.example.rafaelanastacioalves.moby.pulllisting.PullRequestsFragment;

import timber.log.Timber;

public class RepoListingActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private final RecyclerViewClickListener mClickListener = this;
    private RepoListAdapter mRepoListAdapter;
    private RecyclerView mRecyclerView;
    private LiveDataRepoListViewModel mLiveDataRepoListViewModel;

    @Nullable private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        setupRecyclerView();
        subscribe();
    }


    private void subscribe() {
        mLiveDataRepoListViewModel = ViewModelProviders.of(this).get(LiveDataRepoListViewModel.class);

        mLiveDataRepoListViewModel.getMainEntityList().observe(this, mainEntities -> {
            Timber.d("On Changed");
            populateRecyclerView(mainEntities);
        });

        mLiveDataRepoListViewModel.getLoadStatus().observe(this, loadStatus -> {
            //for testing
            tellTestingLoadIsDone(!loadStatus);

            // additionally, we can update screen status
        });
    }

    private void tellTestingLoadIsDone(boolean b) {
        if (getIdlingResource() != null){
            mIdlingResource.setIdleState(b);
        }
    }

    private void setupViews() {
        setContentView(R.layout.repo_listing_activity);
        Timber.tag("LifeCycles");
        Timber.i("onCreate Activity");
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.repo_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        if (mRepoListAdapter == null) {
            mRepoListAdapter = new RepoListAdapter();
        }
        mRepoListAdapter.setRecyclerViewClickListener(mClickListener);
        mRecyclerView.setAdapter(mRepoListAdapter);
    }


    private void populateRecyclerView(PagedList<Repo> data) {
       mRepoListAdapter.submitList(data);

    }


    @Override
    public void onClick(View view, int position) {
        Repo repo = mRepoListAdapter.getCurrentList().get(position);
        Intent i = new Intent(this, PullRequestsActivity.class);
        i.putExtra(PullRequestsFragment.ARG_CREATOR, repo.getOwner().getLogin());
        i.putExtra(PullRequestsFragment.ARG_REPOSITORY, repo.getName());
        startActivity(i);
    }

    public RepoListAdapter getAdapter() {
        return mRepoListAdapter;
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
}
