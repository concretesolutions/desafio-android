package com.example.sharked.accenture.views.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharked.accenture.BaseActivity;
import com.example.sharked.accenture.R;
import com.example.sharked.accenture.adapters.RepositoryAdapter;
import com.example.sharked.accenture.models.InfoContainer;
import com.example.sharked.accenture.models.Repository;
import com.example.sharked.accenture.webservices.SearchRepositories;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private static final int VISIBLE_DO_CALL = 25;

    RepositoryAdapter adapter;

    @ViewById(R.id.repository_list_view)
    ListView repositoryListView;


    @ViewById(R.id.repository_recycler)
    RecyclerView repositoryRecycler;


    private SearchRepositories call = new SearchRepositories(0);
    private LinearLayoutManager recyclerLayoutManager;
    private RepositoryAdapter mAdapter;
    private ArrayList<Repository> mItems = new ArrayList<>();

    private int page = 0;


    @AfterViews
    void init() {

        Log.i("MainActivity", "___init____");
        initRecycler();


    }

    private void initRecycler(){
        recyclerLayoutManager = new LinearLayoutManager(this);
        repositoryRecycler.setLayoutManager(recyclerLayoutManager);
        mAdapter = new RepositoryAdapter(this, mItems);
        repositoryRecycler.setAdapter(mAdapter);
        repositoryRecycler.addOnScrollListener(getOnScrollEvent());
    }

    private RecyclerView.OnScrollListener getOnScrollEvent(){
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemvisible = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (((call.getStatus() == AsyncTask.Status.FINISHED)) && (mItems.size() - lastItemvisible <= VISIBLE_DO_CALL)) {
                    getNewRepositoryPage();
                }
            }
        };
    }

    private void getNewRepositoryPage(){
        call = new SearchRepositories(page++);
        call.execute();
    }




    @Override
    public void onResume() {
        super.onResume();

        //TODO: Handle request from database
        mItems.clear();
        page = 0;
        initRecycler();
        getNewRepositoryPage();
    }

    @Subscribe
    void handleResponse(InfoContainer response) {
        mItems.addAll(Arrays.asList(response.getItems()));
        mAdapter.notifyDataSetChanged();

    }


}
