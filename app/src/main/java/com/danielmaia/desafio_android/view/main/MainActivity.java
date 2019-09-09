package com.danielmaia.desafio_android.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.danielmaia.desafio_android.AppRepo;
import com.danielmaia.desafio_android.R;
import com.danielmaia.desafio_android.listeners.AppListeners;
import com.danielmaia.desafio_android.listeners.ListListener;
import com.danielmaia.desafio_android.model.Owner;
import com.danielmaia.desafio_android.model.Repo;
import com.danielmaia.desafio_android.service.list.ListService;
import com.danielmaia.desafio_android.util.Fonts;
import com.danielmaia.desafio_android.util.Util;
import com.danielmaia.desafio_android.view.pull.PullActivity;
import com.paginate.Paginate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ListListener, MainAdapter.MainAdapterListener,
        SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.rvListRepo)
    RecyclerView rvListRepo;

    @BindView(R.id.rlListContainer)
    RelativeLayout rlListContainer;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @BindView(R.id.txtEmpty)
    TextView txtEmpty;

    int page;
    private Paginate paginate;
    private boolean loadingInProgress = false;
    private boolean hasLoadedAllItems = false;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        txtEmpty.setTypeface(Typeface.createFromAsset(AppRepo.getInstance().getAssets(), Fonts.RALEWAY_MEDIUM));

        refresh.setOnRefreshListener(this);
        refresh.setRefreshing(true);
        refresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Repo.deleteAll(Repo.class);
        page = 1;
        callService();
    }

    private void callService(){
        if (Util.isNetworkAvailable()) {
            loadingInProgress = true;
            AppListeners.getInstance().addListListener(this);
            new ListService(page);
        } else {
            stopSwipeRefresh();
            checkEmptyList();
            Toast.makeText(this, "Verifique a conexão com a internet!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkEmptyList(){
        if (Repo.findAll().size() == 0) {
            rlListContainer.setVisibility(View.GONE);
            txtEmpty.setVisibility(View.VISIBLE);
        } else {
            rlListContainer.setVisibility(View.VISIBLE);
            txtEmpty.setVisibility(View.GONE);
        }
    }

    private void updateAdapter(){
        Paginate.Callbacks callbacks = new Paginate.Callbacks() {
            @Override
            public void onLoadMore() {
                page = page + 1;
                callService();
            }

            @Override
            public boolean isLoading() {
                return loadingInProgress;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return hasLoadedAllItems;
            }
        };

        adapter = new MainAdapter(this, Repo.findAll());

        rvListRepo.setHasFixedSize(true);
        rvListRepo.setLayoutManager(new LinearLayoutManager(this));
        rvListRepo.setAdapter(adapter);

        paginate = Paginate.with(rvListRepo, callbacks)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .build();
    }

    @Override
    public void onRefresh() {
        Repo.deleteAll(Repo.class);
        page = 1;
        callService();
    }

    private void stopSwipeRefresh(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (refresh.isRefreshing())
                    refresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onItemClick(long ownerId, String repo) {
        Owner owner = Owner.findByGuid(ownerId);

        if (owner != null) {
            Intent intent = new Intent(this, PullActivity.class);
            intent.putExtra(PullActivity.PARAM_OWNER, owner.getLogin());
            intent.putExtra(PullActivity.PARAM_REPO, repo);
            startActivity(intent);
        } else
            Toast.makeText(this, getResources().getString(R.string.main_err_owner_null), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListError(String message){
        threadUpdateData(false, false);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListSuccess(boolean hasMore){
        threadUpdateData(true, hasMore);
    }

    private void threadUpdateData(final boolean success, final boolean hasMore) {
        stopSwipeRefresh();

        new Thread() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AppListeners.getInstance().removeListListener(MainActivity.this);
                            List<Repo> list = Repo.findAll();

                            if (success) {
                                if (page == 1)
                                    updateAdapter();
                                else
                                    adapter.setList(list);

                                loadingInProgress = false;
                                hasLoadedAllItems = !hasMore;
                                paginate.setHasMoreDataToLoad(hasMore);
                            }

                            checkEmptyList();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
















