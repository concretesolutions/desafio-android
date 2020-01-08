package br.com.guilherme.concrete.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import br.com.guilherme.concrete.R;
import br.com.guilherme.concrete.adapter.RepositorioAdapter;
import br.com.guilherme.concrete.model.Repositorio;
import br.com.guilherme.concrete.presenter.RepositorioPresenter;

public class MainActivity extends AppCompatActivity implements RepositorioPresenter.View{
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView listRepo;
    private RepositorioPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = findViewById(R.id.coordinator);
        progressBar = findViewById(R.id.loading_content);
        listRepo = findViewById(R.id.list_rpositorios);

        presenter = new RepositorioPresenter(this);
        presenter.getAllRepositorios(1);
    }

    @Override
    public void setRecyclerView(List<Repositorio> repositorios) {
        progressBar.setVisibility(View.GONE);
        listRepo.setVisibility(View.VISIBLE);

        RepositorioAdapter adapter = new RepositorioAdapter(repositorios, MainActivity.this, presenter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        listRepo.setLayoutManager(linearLayoutManager);
        listRepo.setAdapter(adapter);
        listRepo.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onError(String errorException) {
        progressBar.setVisibility(View.GONE);
        Snackbar.make(coordinatorLayout, errorException, Snackbar.LENGTH_LONG).show();
    }
}
