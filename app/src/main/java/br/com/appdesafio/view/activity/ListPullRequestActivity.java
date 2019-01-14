package br.com.appdesafio.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import br.com.appdesafio.R;
import br.com.appdesafio.databinding.ActivityPullRequestBinding;
import br.com.appdesafio.view.adapter.ListPullRequestAdapter;
import br.com.appdesafio.viewmodel.ListPullRequestViewModel;

public class ListPullRequestActivity extends BaseActivity {

    ActivityPullRequestBinding activityPullRequestBinding;
    private RecyclerView recyclerView;
    private ListPullRequestAdapter adapter;
    @Inject
    public ListPullRequestViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPullRequestBinding = DataBindingUtil.setContentView(this, R.layout.activity_pull_request);
        configureRecyclerView();
        configureToolbar();
        getListPullRequest();


    }

    public void configureToolbar() {
        Toolbar toolbar = activityPullRequestBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

    }

    public void configureRecyclerView() {
        recyclerView = activityPullRequestBinding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new ListPullRequestAdapter(this);
    }

    public void getListPullRequest() {
        String creator = getIntent().getStringExtra("creator");
        String repository = getIntent().getStringExtra("repository");
        mViewModel.getListPullRequest(creator, repository).observe(this, result -> {

            if (result != null && result.size() > 0) {
                adapter.setRepository(result);
                recyclerView.setAdapter(adapter);

            } else if (result != null && result.size() == 0) {
                activityPullRequestBinding.emptyState.setBackground(getDrawable(R.drawable.empty_state_pull_request));
                activityPullRequestBinding.emptyState.setVisibility(View.VISIBLE);
            } else {
                //aviso de erro
                activityPullRequestBinding.emptyState.setVisibility(View.VISIBLE);
            }

            activityPullRequestBinding.itemProgressBar.setVisibility(View.GONE);

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
