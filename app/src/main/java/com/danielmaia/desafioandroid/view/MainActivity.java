package com.danielmaia.desafioandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.danielmaia.desafioandroid.App;
import com.danielmaia.desafioandroid.util.AppPreferences;
import com.danielmaia.desafioandroid.util.EndlessRecyclerViewScrollListener;
import com.danielmaia.desafioandroid.viewmodel.MainActivityViewModel;
import com.danielmaia.desafioandroid.R;
import com.danielmaia.desafioandroid.model.Repo;
import com.danielmaia.desafioandroid.view.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainAdapter.MainAdapterListener,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rvListRepo)
    RecyclerView rvListRepo;

    @BindView(R.id.rlListContainer)
    RelativeLayout rlListContainer;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @BindView(R.id.txtEmpty)
    TextView txtEmpty;

    @BindView(R.id.progress)
    ProgressBar progress;

    int page;
    private MainAdapter adapter;
    private MainActivityViewModel mainViewModel;
    private EndlessRecyclerViewScrollListener scrollListener;
    private List<Repo> repoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
    }

    @Override
    protected void onResume() {
        super.onResume();
        page = 1;

        mainViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        repoList.clear();
        getRepos();
    }

    private void prepareRecyclerView(final List<Repo> r) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (page == 1)
                    updateAdapter();
                else
                    adapter.setList(r);

                checkEmptyList();
            }
        });
    }

    private void getRepos(){
        refresh.setRefreshing(true);

        mainViewModel.getListRepos(page).observe(this, l -> {
            if (l != null) {
                refresh.setRefreshing(false);
                repoList.addAll(l);
                prepareRecyclerView(repoList);
            } else {
                Toast.makeText(App.getInstance(), App.getInstance()
                        .getString(R.string.error_service), Toast.LENGTH_SHORT).show();
                checkEmptyList();
            }
        });
    }

    private void checkEmptyList(){
        if (repoList.size() == 0) {
            rlListContainer.setVisibility(View.GONE);
            txtEmpty.setVisibility(View.VISIBLE);
        } else {
            rlListContainer.setVisibility(View.VISIBLE);
            txtEmpty.setVisibility(View.GONE);
        }
    }

    private void updateAdapter(){
        adapter = new MainAdapter(this, repoList);

        rvListRepo.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListRepo.setLayoutManager(linearLayoutManager);
        rvListRepo.setAdapter(adapter);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int p, int totalItemsCount, RecyclerView view) {
                if (repoList.size() < AppPreferences.getInstance().getTotalItems()) {
                    page = page + 1;
                    getRepos();
                }
            }
        };

        rvListRepo.addOnScrollListener(scrollListener);
    }

    @Override
    public void onRefresh() {
        page = 1;
        getRepos();
    }

    @Override
    public void onItemClick(Repo repo) {
        if (repo != null) {
            Intent intent = new Intent(this, PullActivity.class);
            intent.putExtra(PullActivity.PARAM_OWNER, repo.getOwner());
            intent.putExtra(PullActivity.PARAM_REPO, repo.getName());
            startActivity(intent);
        } else
            Toast.makeText(this, getResources().getString(R.string.main_err_owner_null), Toast.LENGTH_SHORT).show();
    }
}
