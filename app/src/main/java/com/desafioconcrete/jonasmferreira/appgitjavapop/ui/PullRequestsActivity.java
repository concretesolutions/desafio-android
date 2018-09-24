package com.desafioconcrete.jonasmferreira.appgitjavapop.ui;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.desafioconcrete.jonasmferreira.appgitjavapop.R;
import com.desafioconcrete.jonasmferreira.appgitjavapop.adapter.PullRequestAdapter;
import com.desafioconcrete.jonasmferreira.appgitjavapop.controller.RepositoryController;
import com.desafioconcrete.jonasmferreira.appgitjavapop.domain.PullRequest;
import com.desafioconcrete.jonasmferreira.appgitjavapop.utils.EndlessRecyclerViewScrollListener;
import com.desafioconcrete.jonasmferreira.appgitjavapop.utils.RecyclerItemClickListener;
import com.desafioconcrete.jonasmferreira.appgitjavapop.utils.SimpleDividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PullRequestsActivity extends BaseActivity {
    public static String TAG = PullRequestsActivity.class.getSimpleName();
    protected ProgressDialog progressDialog;
    protected BroadcastReceiver mMainReceiver;
    private Context context;
    private android.support.v7.widget.RecyclerView rvPulls;
    LinearLayoutManager linearLayoutManager;
    List<PullRequest> pullrequests = new ArrayList<>();
    PullRequestAdapter adapter = new PullRequestAdapter(pullrequests);
    private EndlessRecyclerViewScrollListener scrollListener;
    String criador = "";
    String repositorio = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra("repositorio"));
        setContentView(R.layout.activity_pull_requests);
        context = this;
        loadComponents();
        loadActions();
        loadData();
    }
    protected void loadComponents(){
        this.rvPulls = (RecyclerView) findViewById(R.id.rvPulls);
        linearLayoutManager = new LinearLayoutManager(this);
        rvPulls.setLayoutManager(linearLayoutManager);
        rvPulls.addItemDecoration(new SimpleDividerItemDecoration(this));
    }

    protected void loadData() {
        rvPulls.setAdapter(adapter);
        showProgress("Buscando mais itens");
        criador = getIntent().getStringExtra("criador");
        repositorio = getIntent().getStringExtra("repositorio");
        RepositoryController.getPullRequests(criador,repositorio);
    }
    protected void loadBroadcasts() {
        mMainReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                try {
                    switch (action) {
                        case "desafio-android-get-pull-request":
                            closeProgress();
                            if(!intent.getExtras().getBoolean("success")){
                                return;
                            }
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<PullRequest>>() {}.getType();
                            List<PullRequest> list = gson.fromJson(intent.getExtras().getString("pulls"), type);
                            pullrequests.addAll(list);
                            final int curSize = adapter.getItemCount();
                            Log.d(TAG, list.toString());
                            rvPulls.post(new Runnable() {
                                @Override
                                public void run() {
                                    if(pullrequests.size() > 1) {
                                        adapter.notifyItemRangeInserted(curSize, pullrequests.size() - 1);
                                    }else{
                                        adapter.notifyItemRangeInserted(curSize, pullrequests.size());
                                    }
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
        filter.addAction("desafio-android-get-pull-request");
        LocalBroadcastManager.getInstance(this).registerReceiver(mMainReceiver, filter);
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
    protected void loadActions(){
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                RepositoryController.getPullRequests(criador,repositorio,String.valueOf(page));
            }
        };
        rvPulls.addOnScrollListener(scrollListener);
        rvPulls.addOnItemTouchListener(
                new RecyclerItemClickListener(context, rvPulls ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        PullRequest pr = pullrequests.get(position);
                        String url = pr.getHtmlUrl();
                        if (!url.startsWith("https://") && !url.startsWith("http://")){
                            url = "http://" + url;
                        }
                        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(openUrlIntent);
                    }
                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }
}
