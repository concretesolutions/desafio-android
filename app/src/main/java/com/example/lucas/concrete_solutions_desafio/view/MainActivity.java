package com.example.lucas.concrete_solutions_desafio.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.example.lucas.concrete_solutions_desafio.R;
import com.example.lucas.concrete_solutions_desafio.contract.MainContract;
import com.example.lucas.concrete_solutions_desafio.model.Repository;
import com.example.lucas.concrete_solutions_desafio.model.RepositoryList;
import com.example.lucas.concrete_solutions_desafio.presenter.MainActivityPresenter;
import com.example.lucas.concrete_solutions_desafio.view.adapter.RepositoryAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainActivityPresenter presenter = new MainActivityPresenter(this);
    private RecyclerView recyclerView;
    private RepositoryAdapter adapter;
    private ProgressBar initialProgressBar;
    private ProgressBar refillProgressBar;
    private RepositoryList repositories = new RepositoryList();
    private int repositoriesOldSize = 0;
    private int repositoriesActualSize = 0;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    int pageNumber = 1;
    private boolean loading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refillProgressBar = findViewById(R.id.mainActivity_pbRefillProgressBar);
        initialProgressBar = findViewById(R.id.mainActivity_pbInitialProgressBar);
        recyclerView = findViewById(R.id.mainActivity_rvRepositoryList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        presenter.repositoryRequisition(pageNumber, getApplicationContext());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0)
                {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                    if (!loading)
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading=true;
                            loadData();
                        }
                }
            }
        });
    }
    private void loadData() {
        refillProgressBar.setVisibility(View.VISIBLE);
        pageNumber++;
        presenter.repositoryRequisition(pageNumber, getApplicationContext());
    }
    @Override
    public void fillScreen (RepositoryList repositories){
        this.repositories.setRepositories(repositories);
        adapter = new RepositoryAdapter(this.repositories, this, this);
        recyclerView.setAdapter(adapter);
        repositoriesActualSize = this.repositories.repositoriesCount();
        initialProgressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void refillScreen(RepositoryList repositories) {
        this.repositories.setRepositories(repositories);
        repositoriesOldSize = repositoriesActualSize;
        repositoriesActualSize = this.repositories.repositoriesCount();
        adapter.notifyItemRangeInserted(repositoriesOldSize, repositoriesActualSize);
        loading = false;
        refillProgressBar.setVisibility(View.GONE);
    }
    @Override
    public void interruptProgressBar(){
        initialProgressBar.setVisibility(View.GONE);
        refillProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void onRepositoryClicked(Repository repository) {
        Intent intent = new Intent(this, PullRequestActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("name", repository.getName());
        bundle.putString("description", repository.getDescription());
        bundle.putString("fork_count", repository.getForks_count());
        bundle.putString("stargazers_count", repository.getStargazers_count());
        bundle.putString("full_name", repository.getFull_name());
        bundle.putInt("color_details", repository.getColorDetails());
        intent.putExtras(bundle);
        startActivity(intent,bundle);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
