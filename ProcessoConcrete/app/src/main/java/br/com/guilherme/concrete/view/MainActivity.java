package br.com.guilherme.concrete.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import br.com.guilherme.concrete.R;
import br.com.guilherme.concrete.adapter.RepositorioAdapter;
import br.com.guilherme.concrete.model.Repositorio;
import br.com.guilherme.concrete.presenter.RepositorioPresenter;

public class MainActivity extends AppCompatActivity implements RepositorioPresenter.View {
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView listRepo;
    private RepositorioPresenter presenter;
    private ProgressBar progressBar;
    private int pageToLoad = 1;
    private boolean isLoading = false;
    private List<Repositorio> repositorios;
    private RepositorioAdapter adapter;
    private boolean isFirstPage = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = findViewById(R.id.coordinator);
        progressBar = findViewById(R.id.loading_content);
        listRepo = findViewById(R.id.list_rpositorios);

        repositorios = new ArrayList<>();

        presenter = new RepositorioPresenter(this);
        presenter.getAllRepositorios(pageToLoad);
    }

    @Override
    public void setRecyclerView(final List<Repositorio> repositoriosFromApi) {
        isLoading = false;
        progressBar.setVisibility(View.GONE);
        listRepo.setVisibility(View.VISIBLE);

        int lastPosition = repositorios.size();
        repositorios.addAll(repositoriosFromApi);
        if (isFirstPage){
            adapter = new RepositorioAdapter(repositorios, MainActivity.this, presenter);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

            listRepo.setLayoutManager(linearLayoutManager);
            listRepo.setAdapter(adapter);
            listRepo.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

            repositorios.addAll(repositoriosFromApi);
            isFirstPage = false;
        }
        adapter.notifyDataSetChanged();
        listRepo.scrollToPosition(lastPosition);

        listRepo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) listRepo.getLayoutManager();
                if (!isLoading && linearLayoutManager.findLastVisibleItemPosition() == repositorios.size() -1){
                    isLoading = true;
                    presenter.getAllRepositorios(++pageToLoad);
                }
            }
        });
    }

    @Override
    public void onError(String errorException) {
        progressBar.setVisibility(View.GONE);
        Snackbar.make(coordinatorLayout, errorException, Snackbar.LENGTH_LONG).show();
    }
}
