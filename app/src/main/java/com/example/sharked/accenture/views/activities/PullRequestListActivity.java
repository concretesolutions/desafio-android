package com.example.sharked.accenture.views.activities;

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

@EActivity(R.layout.activity_main)
public class PullRequestListActivity extends BaseActivity {
    PullRequestAdapter adapter;
    public static final String REPOSITORY_EXTRA = "repo";

    @Extra
    Serializable repo;


    @ViewById(R.id.repository_list_view)
    ListView repositoryListView;


    @AfterViews
    void init(){
        Log.i("init","___init____");
        //Toast.makeText(this,"___init____", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResume() {
        super.onResume();
        new SearchPRRequest(((Repository) repo)).execute();

    }

    @Subscribe
    void handleResponse (PullRequest[] pullRequests){
        Log.i("handleResponse",String.valueOf(pullRequests.length));
        for (PullRequest item : pullRequests){Log.i("PR",item.getTitle());}
        if (null == adapter){
            adapter = new PullRequestAdapter(this,pullRequests);
            repositoryListView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }





}
