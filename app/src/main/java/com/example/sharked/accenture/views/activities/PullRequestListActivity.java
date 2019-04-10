package com.example.sharked.accenture.views.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.example.sharked.accenture.BaseActivity;
import com.example.sharked.accenture.R;
import com.example.sharked.accenture.adapters.PullRequestAdapter;
import com.example.sharked.accenture.models.PullRequest;
import com.example.sharked.accenture.models.Repository;
import com.example.sharked.accenture.webservices.SearchPRRequest;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@EActivity(R.layout.activity_main)
public class PullRequestListActivity extends BaseActivity {
    public static final String REPOSITORY_EXTRA = "repo";

    @Extra
    Serializable repo;

    @ViewById(R.id.repository_recycler)
    public  RecyclerView pullRequestRecycler;
    private PullRequestAdapter mAdapter;
    private LinearLayoutManager recyclerLayoutManager;
    private ArrayList<PullRequest> mItems = new ArrayList<>();


    @AfterViews
    void init() {
        Log.i("PullRequestListActivity", "___init____");
        recyclerLayoutManager = new LinearLayoutManager(this);
        pullRequestRecycler.setLayoutManager(recyclerLayoutManager);
        mAdapter = new PullRequestAdapter(this, mItems);
        pullRequestRecycler.setAdapter(mAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        new SearchPRRequest(((Repository) repo).getFullName()).execute();

    }

    @Subscribe
    void handleResponse(PullRequest[] pullRequests) {
        Log.i("handleResponse", String.valueOf(pullRequests.length));
        for (PullRequest item : pullRequests) {Log.i("PR", item.getTitle());}
        mItems.addAll(Arrays.asList(pullRequests));
        mAdapter.notifyDataSetChanged();
    }


}
