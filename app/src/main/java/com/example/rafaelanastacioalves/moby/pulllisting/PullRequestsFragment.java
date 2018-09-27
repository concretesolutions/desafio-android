package com.example.rafaelanastacioalves.moby.pulllisting;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.vo.Pull;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class PullRequestsFragment extends Fragment implements RecyclerViewClickListener {

    public static String ARG_PACKAGE_ID;
    public static final String ARG_CREATOR = "creator_arg";
    public static final String ARG_REPOSITORY = "repository_arg";
    private LiveDataEntityDetailsViewModel mLiveDataEntityDetailsViewModel;
    private final RecyclerViewClickListener clickListener = this;
    @BindView(R.id.pulls_list_recycler_view)
    RecyclerView mPullsListRecyclerView;
    private PullsListAdapter mPullsListAdapter;



    public PullRequestsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subscribe();
        loadData();
    }

    private void loadData() {
        String mCreatorString = getArguments().getString(ARG_CREATOR);
        String mRepositoryString = getArguments().getString(ARG_REPOSITORY);
        mLiveDataEntityDetailsViewModel.loadData(mCreatorString,mRepositoryString);
    }

    private void subscribe() {
        mLiveDataEntityDetailsViewModel = ViewModelProviders.of(this).get(LiveDataEntityDetailsViewModel.class);
        mLiveDataEntityDetailsViewModel.getEntityDetails().observe(this, new Observer<ArrayList<Pull>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Pull> pull) {
                setViewsWith(pull);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pulls_list_fragment, container, false);
        ButterKnife.bind(this, rootView);

        setupRecyclerView(mPullsListRecyclerView);
        return rootView;    }


    private View inflateViews(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.pulls_list_fragment, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        if (mPullsListAdapter == null) {
            mPullsListAdapter = new PullsListAdapter(getContext());
        }
        mPullsListAdapter.setRecyclerViewClickListener(clickListener);
        recyclerView.setAdapter(mPullsListAdapter);
    }

    private void setupActionBarWithTitle(String title) {
        AppCompatActivity mActivity = (AppCompatActivity) getActivity();
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }
    }

    private void setViewsWith(ArrayList<Pull> pull) {
        mPullsListAdapter.setItems(pull);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onClick(View view, int position) {

        Pull aPull = (Pull) view.getTag();
        Timber.i("Url: " + Uri.parse(aPull.getPullUrl()));
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(aPull.getPullUrl()));
        startActivity(browserIntent);
    }
}
