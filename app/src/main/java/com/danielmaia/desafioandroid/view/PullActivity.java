package com.danielmaia.desafioandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.danielmaia.desafioandroid.App;
import com.danielmaia.desafioandroid.viewmodel.PullActivityViewModel;
import com.danielmaia.desafioandroid.R;
import com.danielmaia.desafioandroid.model.Pull;
import com.danielmaia.desafioandroid.view.adapter.PullAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.VISIBLE;

public class PullActivity extends AppCompatActivity implements PullAdapter.PullAdapterListener,
        SwipeRefreshLayout.OnRefreshListener {

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
    private PullActivityViewModel pullActivityViewModel;
    private List<Pull> pullList = new ArrayList<>();

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

        refresh.setOnRefreshListener(this);
        refresh.setRefreshing(true);
        refresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
    }

    @Override
    protected void onResume() {
        super.onResume();

        pullActivityViewModel = ViewModelProviders.of(this).get(PullActivityViewModel.class);
        getPulls();
    }

    private void getPulls(){
        refresh.setRefreshing(true);

        pullActivityViewModel.getListRepos(owner, repo).observe(this, l -> {
            if (l != null) {
                getPullCount();
                refresh.setRefreshing(false);
                pullList = l;
                updateAdapter();
            } else {
                Toast.makeText(App.getInstance(), App.getInstance()
                        .getString(R.string.error_service), Toast.LENGTH_SHORT).show();
            }

            checkEmptyList();
        });
    }

    private void getPullCount(){
        pullActivityViewModel.getOpenedCount(repo).observe(this, count -> {
            if (count >= 0)
                txtOpened.setText(String.format(getString(R.string.pull_txt_opened), count));
            else
                llStatus.setVisibility(View.GONE);
        });

        pullActivityViewModel.getClosedCount(repo).observe(this, count -> {
            if (count >= 0)
                txtClosed.setText(String.format(getString(R.string.pull_txt_closed), count));
            else
                llStatus.setVisibility(View.GONE);
        });
    }

    private void updateAdapter(){
        adapter = new PullAdapter(this, pullList);

        rvListPull.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListPull.setLayoutManager(linearLayoutManager);
        rvListPull.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        getPulls();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void checkEmptyList(){
        if (pullList.size() == 0) {
            llStatus.setVisibility(View.GONE);
            rlListContainer.setVisibility(View.GONE);
            txtEmpty.setVisibility(VISIBLE);
        } else {
            llStatus.setVisibility(VISIBLE);
            rlListContainer.setVisibility(VISIBLE);
            txtEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
