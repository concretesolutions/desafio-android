package com.desafioconcret;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.desafioconcret.adapters.GitAdapter;
import com.desafioconcret.adapters.PullAdapter;
import com.desafioconcret.net.GitHubApiService;
import com.desafioconcret.pojo.json.PullRequests;
import com.desafioconcret.pojo.json.Repositories;
import com.desafioconcret.pojo.json.TopRepositorios;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PullRequestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PullAdapter adapter;

    private String creator;
    private String repositories;
    private Toolbar toolbar;

    GitHubApiService gitHubApiService = new GitHubApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.pulls_recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SimpleDivider(this));

        Bundle bundle = getIntent().getExtras();
        creator = bundle.getString("OWNER");
        repositories = bundle.getString("REPO");

        toolbar = findViewById(R.id.toolbar_pullrequest);
        toolbar.setNavigationIcon(R.drawable.ic_action_goleft);
        toolbar.setTitle(repositories);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        mostrarListapull();

    }

    private void mostrarListapull() {

        Observable<List<PullRequests>> pullRequestsObservable = gitHubApiService
                .topRepositorios().getPulls(creator, repositories);

        pullRequestsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PullRequests>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<PullRequests> pullRequests) {

                        List<PullRequests> pulls = pullRequests;
                        adapter = new PullAdapter(pulls);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("conexção", "Concexão erro" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
