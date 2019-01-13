package br.com.appdesafio.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import javax.inject.Inject;

import br.com.appdesafio.R;
import br.com.appdesafio.databinding.ActivityPullRequestBinding;
import br.com.appdesafio.view.adapter.ListPullRequestAdapter;
import br.com.appdesafio.viewmodel.ListPullRequestViewModel;

public class ListPullRequestActivity extends BaseActivity {

    ActivityPullRequestBinding mBinding;
    private RecyclerView recyclerView;
    private EndlessRecyclerViewScrollListener scrollListener;
    private ListPullRequestAdapter adapter;
    @Inject
    public ListPullRequestViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_pull_request);
        configureRecyclerView();
        String creator = getIntent().getStringExtra("creator");
        String repository = getIntent().getStringExtra("repository");
        configureToolbar();

        mViewModel.getListPullRequest(creator, repository).observe(this, result -> {

            if (result != null) {
                adapter.setRepository(result);
                recyclerView.setAdapter(adapter);
            } else {
                //aviso de erro
                // binding.emptyState.setVisibility(View.VISIBLE);
            }

        });
    }

    public void configureToolbar() {
        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

    }

    public void configureRecyclerView() {
        recyclerView = mBinding.recyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //addDataToList(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);

        adapter = new ListPullRequestAdapter(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
