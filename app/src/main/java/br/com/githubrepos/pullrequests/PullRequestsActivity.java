package br.com.githubrepos.pullrequests;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import br.com.githubrepos.Injection;
import br.com.githubrepos.R;
import br.com.githubrepos.commons.ItemListListener;
import br.com.githubrepos.data.entity.PullRequest;

public class PullRequestsActivity extends AppCompatActivity implements PullRequestsContract.View {

    private PullRequestsContract.UserActionsListener mActionsListener;
    private SwipeRefreshLayout mSwipeRefresh;
    private PullRequestsAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private ItemListListener<PullRequest> mPullRequestItemListener = new ItemListListener<PullRequest>() {
        @Override
        public void onClickedItem(PullRequest item) {
            //TODO open browser
            //mActionsListener.openPullRequestDetails();
        }

        @Override
        public void onLongClickedSelectItem(int index) {
        }

        @Override
        public void onLongClickedUnselectItem(int index) {
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("pullRequestList", mAdapter.getList());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullrequests);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        // show or hide the default home button
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // enable overriding the default toolbar layout
        actionBar.setDisplayShowCustomEnabled(true);
        // enable the default title element here (for centered title)
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("PullRequests");

        mActionsListener = new PullRequestsPresenter(Injection.providePullRequestServiceApi(), this);

        setupActionBar();
        setupContentView();

        loadData(savedInstanceState);
    }

    private void setupActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        // enable overriding the default toolbar layout
        actionBar.setDisplayShowCustomEnabled(true);
        // enable the default title element here (for centered title)
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Pull requests");
    }

    private void setupContentView() {
        mRecyclerView = findViewById(R.id.pullrequests_list);
        mRecyclerView.setHasFixedSize(true);

        int numColuns = getResources().getInteger(R.integer.num_pullrequests_columns);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numColuns);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mAdapter = new PullRequestsAdapter(new ArrayList<PullRequest>(), mRecyclerView,
                mPullRequestItemListener);

        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefresh = findViewById(R.id.refresh_layout);
        mSwipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.loadPullRequestList(true);
            }
        });
    }

    private void loadData(Bundle savedInstanceState) {
        if (null != savedInstanceState) {
            mAdapter.addData((CopyOnWriteArrayList<PullRequest>) savedInstanceState.get("pullRequestList"));
        } else {
            mActionsListener.loadPullRequestList(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO testar
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setProgressIndicator(final boolean isActive) {
        mSwipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefresh.setRefreshing(isActive);
            }
        });
    }

    @Override
    public void showPullRequestList(List<PullRequest> pullRequestList) {
        mAdapter.replaceData(pullRequestList);
    }

    @Override
    public void showPullRequestInBrowser() {
        //TODO
    }
}
