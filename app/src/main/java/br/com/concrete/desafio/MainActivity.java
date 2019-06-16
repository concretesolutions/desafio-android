package br.com.concrete.desafio;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import br.com.concrete.desafio.model.Repository;
import br.com.concrete.desafio.model.SearchRepositoryResult;
import br.com.concrete.desafio.resource.Github;
import br.com.concrete.desafio.until.JsonService;
import br.com.concrete.desafio.until.RepositoryAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter repositoryAdapter;
    private LinearLayoutManager layoutManager;
    private ConstraintLayout clLoading;
    private int currentPageNumber;
    private boolean allowListToUpdate;
    private Parcelable recycleViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repositoryAdapter = new RepositoryAdapter(null);
        layoutManager = new LinearLayoutManager(this);
        clLoading = findViewById(R.id.clLoading);
        currentPageNumber = 0;
        allowListToUpdate = false;
        recycleViewState = null;

        initRecyclerView();

        if (savedInstanceState == null) getRepositories();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        recycleViewState = layoutManager.onSaveInstanceState();

        outState.putInt("PAGE_NUMBER", currentPageNumber);
        outState.putParcelable("RV_STATE", recycleViewState);
        outState.putParcelableArrayList("RV_ITEMS", ((RepositoryAdapter) repositoryAdapter).getDataSet());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            currentPageNumber = savedInstanceState.getInt("PAGE_NUMBER");
            recycleViewState = savedInstanceState.getParcelable("RV_STATE");
            ((RepositoryAdapter) repositoryAdapter).setDataSet(savedInstanceState.<Repository>getParcelableArrayList("RV_ITEMS"));
            repositoryAdapter.notifyDataSetChanged();
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

    private void initRecyclerView() {
        final RecyclerView rvRepositoryList = findViewById(R.id.rvRepositoryList);

        rvRepositoryList.setLayoutManager(layoutManager);
        rvRepositoryList.setAdapter(repositoryAdapter);

        rvRepositoryList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (lastVisibleItem > repositoryAdapter.getItemCount() - 5 && allowListToUpdate) {
                    allowListToUpdate = false;
                    getRepositories();
                }
            }
        });

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(this, R.drawable.divider);
        if (divider != null) {
            itemDecorator.setDrawable(divider);
        }
        rvRepositoryList.addItemDecoration(itemDecorator);
    }

    private void getRepositories() {
        final String url = "https://api.github.com/search/";
        final String[] args = {"language:Java", "stars", Integer.toString(++currentPageNumber)};
        final JsonService<SearchRepositoryResult> jsonService = new JsonService<>(url, getBaseContext());

        clLoading.setVisibility(View.VISIBLE);

        jsonService.setListener(new JsonService.ChangeListener() {
            @Override
            public void onChange() {
                try {
                    ((RepositoryAdapter) repositoryAdapter).addData(jsonService.getData().getItems());
                    repositoryAdapter.notifyDataSetChanged();

                    jsonService.clearDisposable();
                }
                catch (Exception e) {
                    --currentPageNumber;
                    Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_SHORT).show();
                }

                allowListToUpdate = true;
                clLoading.setVisibility(View.GONE);
            }
        });

        jsonService.getJson(Github.class, "getRepositories", args);
    }
}