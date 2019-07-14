package com.felipe.palma.desafioconcrete.ui.pullrequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.felipe.palma.desafioconcrete.R;
import com.felipe.palma.desafioconcrete.domain.model.Item;
import com.felipe.palma.desafioconcrete.domain.response.PullRequestResponse;
import com.felipe.palma.desafioconcrete.ui.adapter.InfiniteScrollListener;
import com.felipe.palma.desafioconcrete.ui.adapter.PullRequestAdapter;
import com.felipe.palma.desafioconcrete.ui.adapter.RecyclerItemClickListener;
import com.felipe.palma.desafioconcrete.ui.adapter.RepositoryAdapter;
import com.felipe.palma.desafioconcrete.ui.adapter.decoration.ItemOffsetDecoration;
import com.felipe.palma.desafioconcrete.ui.repository.RepositoriesActivity;
import com.felipe.palma.desafioconcrete.ui.repository.RepositoriesPresenter;
import com.felipe.palma.desafioconcrete.utils.Config;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullRequestActivity extends AppCompatActivity implements PullRequestContract.View{

    private PullRequestContract.Presenter mPresenter;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
    private List<PullRequestResponse> pullRequestResponseList = new ArrayList<>();
    private String owner, repo;
    private PullRequestAdapter mPullAdapter;

    @BindView(R.id.recycler_view_pull_request) RecyclerView mRecyclerView;

    @BindView(R.id.toobar) Toolbar toolbar;

    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        ButterKnife.bind(this);

        getValues();
        setupToolBar();
        //setupAdapter();
        setupPresenter();
        setupRecyclerView();
    }


    private void getValues(){
        owner = getIntent().getStringExtra(Config.OWNER);
        repo = getIntent().getStringExtra(Config.REPO);
    }

    private void setupPresenter() {
        mPresenter = new PullRequestPresenter(this);
        mPresenter.loadPullRequests(this.owner,this.repo);
    }

    private void setupToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(repo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupAdapter(){
        mPullAdapter = new PullRequestAdapter(this, pullRequestResponseList, recyclerItemClickListener);
        mRecyclerView.setAdapter(mPullAdapter);
    }
    private void setupRecyclerView() {
        final int spacing = getResources().getDimensionPixelOffset(R.dimen.default_spacing_small);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spacing));

//        infiniteScrollListener = new InfiniteScrollListener(mLinearLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                mPresenter.loadRepositories(page);
//
//                Log.d("REPO_TEST", page+"");
//            }
//        };
//
//        mRecyclerView.addOnScrollListener(infiniteScrollListener);
    }


    @Override
    public void showPullRequests(List<PullRequestResponse> response) {
        Log.d("DATA", response.size()+"");
        pullRequestResponseList = response;
        setupAdapter();


    }

    @Override
    public void hideDialog() {
        dialog.hide();
    }

    @Override
    public void showDialog() {
        dialog = ProgressDialog.show(this, "Baixando dados",
                "Aguarde ...", true);

    }


    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                intent = new Intent(getApplicationContext(), RepositoriesActivity.class);
                startActivity(intent);
                break;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener<PullRequestResponse> recyclerItemClickListener = item -> {
       Toast.makeText(this,"CLICK", Toast.LENGTH_LONG).show();
    };
}
