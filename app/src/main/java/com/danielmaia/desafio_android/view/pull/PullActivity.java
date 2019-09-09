package com.danielmaia.desafio_android.view.pull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.danielmaia.desafio_android.AppRepo;
import com.danielmaia.desafio_android.R;
import com.danielmaia.desafio_android.listeners.AppListeners;
import com.danielmaia.desafio_android.listeners.PullListener;
import com.danielmaia.desafio_android.model.Pull;
import com.danielmaia.desafio_android.service.pull.PullService;
import com.danielmaia.desafio_android.util.Fonts;
import com.danielmaia.desafio_android.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.VISIBLE;

public class PullActivity extends AppCompatActivity implements PullListener, PullAdapter.PullAdapterListener,
        SwipeRefreshLayout.OnRefreshListener{

    public static final String PARAM_OWNER = "param_owner";
    public static final String PARAM_REPO = "param_repo";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.llStatus)
    LinearLayout llStatus;

    @BindView(R.id.txtOpened)
    TextView txtOpened;

    @BindView(R.id.txtClosed)
    TextView txtClosed;

    @BindView(R.id.rvListPull)
    RecyclerView rvListPull;

    @BindView(R.id.rlListContainer)
    RelativeLayout rlListContainer;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @BindView(R.id.txtEmpty)
    TextView txtEmpty;

    private String owner;
    private String repo;
    private PullAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        ButterKnife.bind(this);

        owner = getIntent().getStringExtra(PARAM_OWNER);
        repo = getIntent().getStringExtra(PARAM_REPO);

        toolbar.setTitle(repo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtEmpty.setTypeface(Typeface.createFromAsset(AppRepo.getInstance().getAssets(), Fonts.RALEWAY_MEDIUM));
        txtOpened.setTypeface(Typeface.createFromAsset(AppRepo.getInstance().getAssets(), Fonts.RALEWAY_REGULAR));
        txtClosed.setTypeface(Typeface.createFromAsset(AppRepo.getInstance().getAssets(), Fonts.RALEWAY_REGULAR));

        refresh.setOnRefreshListener(this);
        refresh.setRefreshing(true);
        refresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
    }



    @Override
    protected void onResume() {
        super.onResume();

        Pull.deleteAll(Pull.class);
        callService();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void callService(){
        if (Util.isNetworkAvailable()) {
            AppListeners.getInstance().addPullListener(this);
            new PullService(owner, repo);
        } else {
            stopSwipeRefresh();
            checkEmptyList();
            Toast.makeText(this, "Verifique a conexão com a internet!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkEmptyList(){
        if (Pull.findAll().size() == 0) {
            llStatus.setVisibility(View.GONE);
            rlListContainer.setVisibility(View.GONE);
            txtEmpty.setVisibility(VISIBLE);
        } else {
            llStatus.setVisibility(VISIBLE);
            rlListContainer.setVisibility(VISIBLE);
            txtEmpty.setVisibility(View.GONE);
        }
    }

    private void updateAdapter(){
        adapter = new PullAdapter(this, Pull.findAll());

        rvListPull.setHasFixedSize(true);
        rvListPull.setLayoutManager(new LinearLayoutManager(this));
        rvListPull.setAdapter(adapter);

        txtOpened.setText(String.format(getString(R.string.pull_txt_opened), Pull.getOpenedCount()));
        txtClosed.setText(String.format(getString(R.string.pull_txt_closed), Pull.getClosedCount()));
    }

    @Override
    public void onRefresh() {
        Pull.deleteAll(Pull.class);
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
    public void onItemClick(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onPullSuccess() {
        threadUpdateData(true);
    }

    @Override
    public void onPullError(String message) {
        threadUpdateData(false);
    }

    private void threadUpdateData(final boolean success) {
        stopSwipeRefresh();

        new Thread() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AppListeners.getInstance().removePullListener(PullActivity.this);

                            if (success)
                                updateAdapter();

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
