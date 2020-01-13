package br.com.josef.desafioconcretegit.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.josef.desafioconcretegit.R;
import br.com.josef.desafioconcretegit.model.pojo.repositories.Item;
import br.com.josef.desafioconcretegit.view.adapter.GitAdapter;
import br.com.josef.desafioconcretegit.view.interfaces.GitOnClick;
import br.com.josef.desafioconcretegit.viewmodel.RepositorioViewModel;

public class MainActivity extends AppCompatActivity implements GitOnClick {

    private RecyclerView recyclerView;
    private RepositorioViewModel viewModel;
    private GitAdapter adapter;
    private ProgressBar progressBar;
    private List<Item> itemList = new ArrayList<>();
    public static final String LOGIN_ID = "creator_id";
    public static final String REPOSITORY_ID = "repo_id";
    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        setScrollView();

        viewModel.getRepositorios(page);
        viewModel.getListaRespositorios().observe(this, requestList1 -> {
            adapter.atualizalista(requestList1);

        });

        viewModel.getBooleano().observe(this, aBoolean -> {
            if(aBoolean){
                progressBar.setVisibility(View.VISIBLE);
            }else{
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.RecyclerMain);
        viewModel = ViewModelProviders.of(this).get(RepositorioViewModel.class);
        adapter = new GitAdapter(itemList, this);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void OnClick(Item result) {

        Intent intent = new Intent(MainActivity.this, PullRequestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(REPOSITORY_ID, result.getName());
        bundle.putString(LOGIN_ID, result.getOwner().getLogin());
        intent.putExtras(bundle);
        startActivity(intent);


    }

    private void setScrollView(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                boolean ultimoItem = lastVisible + 5 >= totalItemCount;

                if (totalItemCount > 0 && ultimoItem){
                    page++;
                    viewModel.getRepositorios(page);
                }

            }
        });
    }

}
