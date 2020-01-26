package br.com.victoramaral.githubdive.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.victoramaral.githubdive.R;
import br.com.victoramaral.githubdive.model.pojos.repositories.Item;
import br.com.victoramaral.githubdive.view.adapter.RepositoryAdapter;
import br.com.victoramaral.githubdive.view.interfaces.RepositoryOnClick;
import br.com.victoramaral.githubdive.viewmodel.RepositoriesViewModel;

public class RepositoriesActivity extends AppCompatActivity implements RepositoryOnClick {

    private RecyclerView recyclerView;
    private RepositoriesViewModel viewModel;
    private RepositoryAdapter adapter;
    private List<Item> itemList = new ArrayList<>();
    private int page = 1;
    private int perPage = 10;

    public static final String CREATOR_KEY = "123456";
    public static final String REPOSITORY_KEY = "654321";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);
        initView();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getAllRepositories(page, perPage);
        viewModel.getListaRepositories().observe(this, items -> {
            adapter.setUpdate(items);
        });

        scrollListener();
    }

    public void initView() {
        recyclerView = findViewById(R.id.recyclerViewRepo);
        viewModel = ViewModelProviders.of(this).get(RepositoriesViewModel.class);
        adapter = new RepositoryAdapter(itemList, this);
    }

    public void scrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int
                    newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;

                if (totalItemCount > 0 && endHasBeenReached) {
                    page++;
                    viewModel.getAllRepositories(page, perPage);
                }
            }
        });
    }

    @Override
    public void repositoryOnClick(Item item) {
        Toast.makeText(this, "Você selecionou o repositório " + item.getName(),
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RepositoriesActivity.this,
                PullRequestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(CREATOR_KEY, item.getOwner().getLogin());
        bundle.putString(REPOSITORY_KEY, item.getName());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
