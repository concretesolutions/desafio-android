package br.com.erico.desafio_android.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.erico.desafio_android.R;
import br.com.erico.desafio_android.controls.interfaces.Services;
import br.com.erico.desafio_android.controls.structures.RepoStructure;
import br.com.erico.desafio_android.controls.util.ApiGitHub;
import br.com.erico.desafio_android.models.Repository;
import br.com.erico.desafio_android.models.RepositoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories extends AppCompatActivity {

    private RepoStructure structure;
    private RecyclerView listRepository;
    private Services gitHutServices;
    private ProgressBar progressBar;
    private boolean isLoading = false;
    private int currentPage = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        gitHutServices = ApiGitHub.getGitService();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listRepository = (RecyclerView) findViewById(R.id.listRepository);

        structure = new RepoStructure(this, new ArrayList<Repository>(0), new RepoStructure.RepositoryListener() {
            @Override
            public void onRepositoryClick(String authorName, String repoName) {

                Intent intent = new Intent(getApplicationContext(), DescriptionRequest.class);
                intent.putExtra("authorName", authorName);
                intent.putExtra("repoName", repoName);
                startActivity(intent);
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listRepository.setLayoutManager(layoutManager);
        listRepository.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        listRepository.addItemDecoration(itemDecoration);

        if (savedInstanceState != null) {
            List<Repository> repositories = (List<Repository>) savedInstanceState.getSerializable("repositories");
            structure.updateRepositories(repositories);
            this.currentPage = savedInstanceState.getInt("currentPage");
        }

        listRepository.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                isLoading = true;
                int pastVisibleItems, visibleItemsCount, totalItemCount;

                if (dy > 0) {
                    visibleItemsCount = layoutManager.getChildCount();
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    totalItemCount = layoutManager.getItemCount();

                    if (isLoading) {
                        if ((visibleItemsCount + pastVisibleItems) >= totalItemCount) {
                            isLoading = false;
                            progressBar.setVisibility(View.VISIBLE);
                            loadRepositories(++currentPage);
                        }
                    }
                }
            }
        });

        listRepository.setAdapter(structure);
    }

    public void loadRepositories(int page) {
        gitHutServices.getRepositories(page).enqueue(new Callback<RepositoryResponse>() {
            @Override
            public void onResponse(Call<RepositoryResponse> call, Response<RepositoryResponse> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    structure.addAll(response.body().getRepositories());
                    Log.d("Repositories", "Repositories OK.");
                }
            }

            @Override
            public void onFailure(Call<RepositoryResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Carregamento Indevido!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Log.e("Repositories", "Erro no API!");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRepositories(currentPage);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("repositories", (Serializable) structure.getRepositories());
        outState.putInt("currentPage", currentPage);
    }

}
