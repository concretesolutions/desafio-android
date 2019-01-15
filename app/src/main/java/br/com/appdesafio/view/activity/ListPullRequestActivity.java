package br.com.appdesafio.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import br.com.appdesafio.R;
import br.com.appdesafio.databinding.ActivityListPullRequestBinding;
import br.com.appdesafio.model.pojo.PullRequest;
import br.com.appdesafio.view.adapter.ListPullRequestAdapter;
import br.com.appdesafio.viewmodel.ListPullRequestViewModel;

/**
 * class responsible for displaying the pull request list.
 */

public class ListPullRequestActivity extends BaseActivity {

    ActivityListPullRequestBinding activityPullRequestBinding;
    private RecyclerView recyclerView;
    private ListPullRequestAdapter adapter;
    @Inject
    public ListPullRequestViewModel mViewModel;
    public boolean stateOnResume;

    /**
     * Initialize the fundamental components of the activity.
     *
     * @param savedInstanceState
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPullRequestBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_pull_request);
        configureRecyclerView();
        configureToolbar();
        getListPullRequest();


    }


    /**
     * Configure the activity toolbar.
     */
    public void configureToolbar() {
        Toolbar toolbar = activityPullRequestBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

    }

    /**
     * Configure activity recyclerview
     */

    public void configureRecyclerView() {
        recyclerView = activityPullRequestBinding.recyclerPullRequest;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new ListPullRequestAdapter(this);
    }

    /**
     * Method responsible for fetching the pull request list from a repository.
     */
    public void getListPullRequest() {
        String creator = getIntent().getStringExtra("creator");
        String repository = getIntent().getStringExtra("repository");
        mViewModel.getListPullRequest(creator, repository).observe(this, result -> {

            if (result != null && result.size() > 0) {
                adapter.setRepository(result);
                recyclerView.setAdapter(adapter);

            } else if (result != null && result.size() == 0) { /** list empty, server does not return any item**/
                activityPullRequestBinding.emptyState.setText(getString(R.string.message_list_empty));
                activityPullRequestBinding.emptyState.setVisibility(View.VISIBLE);
            } else {
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

    @Override
    protected void onResume() {
        super.onResume();
        this.stateOnResume = true;
    }
}
