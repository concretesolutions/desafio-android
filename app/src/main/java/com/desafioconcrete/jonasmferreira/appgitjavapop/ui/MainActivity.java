package com.desafioconcrete.jonasmferreira.appgitjavapop.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.desafioconcrete.jonasmferreira.appgitjavapop.R;
import com.desafioconcrete.jonasmferreira.appgitjavapop.adapter.RepositoryAdapter;
import com.desafioconcrete.jonasmferreira.appgitjavapop.controller.SearchController;
import com.desafioconcrete.jonasmferreira.appgitjavapop.domain.Repository;
import com.desafioconcrete.jonasmferreira.appgitjavapop.utils.EndlessRecyclerViewScrollListener;
import com.desafioconcrete.jonasmferreira.appgitjavapop.utils.RecyclerItemClickListener;
import com.desafioconcrete.jonasmferreira.appgitjavapop.utils.SimpleDividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public static String TAG = MainActivity.class.getSimpleName();
    protected BroadcastReceiver mMainReceiver;
    private android.support.v7.widget.RecyclerView rvRepositories;
    private EndlessRecyclerViewScrollListener scrollListener;
    LinearLayoutManager linearLayoutManager;
    List<Repository> repositories = new ArrayList<>();
    RepositoryAdapter adapter = new RepositoryAdapter(repositories);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        startWakelock();

        loadComponents();
        loadActions();
        loadData();
    }

    protected void loadComponents() {
        this.rvRepositories = (RecyclerView) findViewById(R.id.rvRepositories);

        linearLayoutManager = new LinearLayoutManager(this);
        rvRepositories.setLayoutManager(linearLayoutManager);
        rvRepositories.addItemDecoration(new SimpleDividerItemDecoration(this));
    }

    protected void loadActions(){
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                SearchController.getRepository(String.valueOf(page));
            }
        };
        rvRepositories.addOnScrollListener(scrollListener);
        rvRepositories.addOnItemTouchListener(
                new RecyclerItemClickListener(context, rvRepositories ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Repository r = repositories.get(position);
                        Intent intent = new Intent(context, PullRequestsActivity.class);
                        intent.putExtra("criador",r.getOwner().getLogin());
                        intent.putExtra("repositorio",r.getName());
                        context.startActivity(intent);
                    }
                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    protected void loadData() {
        rvRepositories.setAdapter(adapter);
        showProgress("Buscando mais itens");
        SearchController.getRepository();
    }

    protected void loadBroadcasts() {
        mMainReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                try {
                    switch (action) {
                        case "desafio-android-get-repository":
                            closeProgress();
                            if(!intent.getExtras().getBoolean("success")){
                                return;
                            }
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Repository>>() {}.getType();
                            List<Repository> repositoryList = gson.fromJson(intent.getExtras().getString("repository"), type);
                            repositories.addAll(repositoryList);
                            final int curSize = adapter.getItemCount();
                            Log.d(TAG, repositoryList.toString());
                            rvRepositories.post(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyItemRangeInserted(curSize, repositories.size() - 1);
                                }
                            });
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("desafio-android-get-repository");
        LocalBroadcastManager.getInstance(this).registerReceiver(mMainReceiver, filter);
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onResume() {
        super.onResume();
        loadBroadcasts();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMainReceiver);
    }
}
