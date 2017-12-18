package br.com.githubrepos.repositories;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import br.com.githubrepos.Injection;
import br.com.githubrepos.R;
import br.com.githubrepos.commons.EndlessScrolling;
import br.com.githubrepos.commons.ItemListListener;
import br.com.githubrepos.data.entity.Repository;
import br.com.githubrepos.pullrequests.PullRequestsActivity;
import br.com.githubrepos.util.IdleResourceHandler;

public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View {

    private RepositoriesContract.UserActionsListener mActionsListener;
    private SwipeRefreshLayout mSwipeRefresh;
    private Menu mMenu;
    private RepositoriesAdapter mAdapter;

    private boolean isAnyRepositorySelected;

    private RecyclerView mRecyclerView;

    private ItemListListener<Repository> mRepositoryItemListener = new ItemListListener<Repository>() {
        @Override
        public void onClickedItem(Repository item) {
            mActionsListener.openRepository(item);
        }

        @Override
        public void onLongClickedSelectItem(int index) {
            mActionsListener.selectRepository(index);
        }

        @Override
        public void onLongClickedUnselectItem(int index) {
            mActionsListener.unselectRepository(index);
        }
    };

    private EndlessScrolling.OnLoadMoreListener mOnLoadMoreListener = new EndlessScrolling.OnLoadMoreListener() {
        @Override
        public void onLoadMore(int page) {
            mActionsListener.loadRepositoryList(page, false);
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("isAnyRepositorySelected", isAnyRepositorySelected);
        outState.putSerializable("repositoryList", mAdapter.getList());
        outState.putSerializable("currentPage", mAdapter.getPage());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        mActionsListener = new RepositoriesPresenter(Injection.provideRepositoryServiceApi(), this);
        isAnyRepositorySelected = false;

        setupActionBar();
        setupContentView();

        loadData(savedInstanceState);
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        // enable overriding the default toolbar layout
        actionBar.setDisplayShowCustomEnabled(true);
        // enable the default title element here (for centered title)
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Repositories");
    }

    private void setupContentView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.repositories_list);
        mRecyclerView.setHasFixedSize(true);

        int numColuns = getResources().getInteger(R.integer.num_movies_columns);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numColuns);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mAdapter = new RepositoriesAdapter(new ArrayList<Repository>(), mRecyclerView,
                mRepositoryItemListener, mOnLoadMoreListener);

        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mSwipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.loadRepositoryList(1, true);

                //TODO this need be called by presenter
                changeActionBarWhenRepositoryUnselected(-1);
            }
        });
    }

    private void loadData(Bundle savedInstanceState) {
        if (null != savedInstanceState) {
            isAnyRepositorySelected = savedInstanceState.getBoolean("isAnyRepositorySelected");
            mAdapter.addData((CopyOnWriteArrayList<Repository>) savedInstanceState.get("repositoryList"));
            mAdapter.setPage(savedInstanceState.getInt("currentPage"));

        } else {
            mActionsListener.loadRepositoryList(1, true);
        }
    }

    //visible for users but don't allow any interaction with them in onResume you are enable to interact
    @Override
    protected void onStart() {
        super.onStart();
        //mActionsListener.loadRepositoryList(1, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.repository_actions, menu);
        this.mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                if (isAnyRepositorySelected) {
                    mActionsListener.unselectRepository(-1);
                }
                break;
            case R.id.delete_repository:
                mActionsListener.deleteSelectedRepository();
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
    public void setListProgressIndicator(final boolean isActive) {
        if (isActive) {
            //TODO java.lang.RuntimeException: Parcel: unable to marshal value br.com.ghrepos.data.entity.Repository@416f24d0

            //TODO analize - runnable
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.addProgressItem();
                }
            });
        } else {
            mAdapter.removeProgressItem();
        }
    }

    @Override
    public void showRepositoryList(List<Repository> repositoryList, boolean doRefresh) {
        if (doRefresh) {
            mAdapter.replaceData(repositoryList);
        } else {
            mAdapter.appendData(repositoryList);
        }
    }

    @Override
    public void changeActionBarWhenRepositorySelected() {
        isAnyRepositorySelected = true;
        this.mMenu.getItem(0).setVisible(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setTitle(null);
    }

    @Override
    public void changeActionBarWhenRepositoryUnselected(int repositoryPosition) {
        isAnyRepositorySelected = false;
        this.mMenu.getItem(0).setVisible(false);

        getSupportActionBar().setTitle("Repositories");
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mAdapter.unselectItem(repositoryPosition);
    }

    @Override
    public void removeRepository(int position) {
        mAdapter.removeItem(position);
    }

    @Override
    public void showPullRequestListUi(String ownerLogin, String repositoryName) {
        Intent intent = new Intent(RepositoriesActivity.this, PullRequestsActivity.class);
        intent.putExtra("ownerLogin", ownerLogin);
        intent.putExtra("repositoryName", repositoryName);
        startActivity(intent);
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return IdleResourceHandler.getIdlingResource();
    }
}
