package br.com.concrete.desafio;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import br.com.concrete.desafio.model.PullRequest;
import br.com.concrete.desafio.resource.Github;
import br.com.concrete.desafio.until.JsonService;
import br.com.concrete.desafio.until.PullRequestAdapter;

public class PullRequestActivity extends AppCompatActivity {
    private RecyclerView.Adapter pullRequestAdapter;
    private LinearLayoutManager layoutManager;
    private ConstraintLayout clLoading;
    private int currentPageNumber;
    private boolean allowListToUpdate;
    private Parcelable recycleViewState;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        pullRequestAdapter = new PullRequestAdapter(null);
        layoutManager = new LinearLayoutManager(this);
        clLoading = findViewById(R.id.clLoading);
        currentPageNumber = 0;
        allowListToUpdate = false;
        recycleViewState = null;
        url = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("URL");
            if (actionBar != null) actionBar.setTitle(extras.getString("NAME"));
        }

        initRecyclerView();

        if (savedInstanceState == null) getPullRequests();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        recycleViewState = layoutManager.onSaveInstanceState();

        outState.putInt("PAGE_NUMBER", currentPageNumber);
        outState.putParcelable("RV_STATE", recycleViewState);
        outState.putParcelableArrayList("RV_ITEMS", ((PullRequestAdapter) pullRequestAdapter).getDataSet());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            currentPageNumber = savedInstanceState.getInt("PAGE_NUMBER");
            recycleViewState = savedInstanceState.getParcelable("RV_STATE");
            ((PullRequestAdapter) pullRequestAdapter).setDataSet(savedInstanceState.<PullRequest>getParcelableArrayList("RV_ITEMS"));
            pullRequestAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (recycleViewState != null) {
            layoutManager.onRestoreInstanceState(recycleViewState);
            recycleViewState = null;
            allowListToUpdate = true;
            clLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    private void initRecyclerView() {
        final RecyclerView rvPullRequestList = findViewById(R.id.rvPullRequestList);

        rvPullRequestList.setLayoutManager(layoutManager);
        rvPullRequestList.setAdapter(pullRequestAdapter);

        rvPullRequestList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (lastVisibleItem > pullRequestAdapter.getItemCount() - 5 && allowListToUpdate) {
                    allowListToUpdate = false;
                    getPullRequests();
                }
            }
        });

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(this, R.drawable.divider);
        if (divider != null) {
            itemDecorator.setDrawable(divider);
        }
        rvPullRequestList.addItemDecoration(itemDecorator);
    }

    private void getPullRequests() {
        final String[] args = {Integer.toString(++currentPageNumber)};
        final JsonService<PullRequest[]> jsonService = new JsonService<>(url, getBaseContext());

        clLoading.setVisibility(View.VISIBLE);

        jsonService.setListener(new JsonService.ChangeListener() {
            @Override
            public void onChange() {
                try {
                    ((PullRequestAdapter) pullRequestAdapter).addData(jsonService.getData());
                    pullRequestAdapter.notifyDataSetChanged();

                    jsonService.clearDisposable();
                }
                catch (Exception e) {
                    --currentPageNumber;
                    Toast.makeText(PullRequestActivity.this, getString(R.string.error), Toast.LENGTH_SHORT).show();
                }

                allowListToUpdate = true;
                clLoading.setVisibility(View.GONE);
            }
        });

        jsonService.getJson(Github.class, "getPullRequests", args);
    }
}
