package br.com.erico.desafio_android.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
import br.com.erico.desafio_android.controls.structures.PullStructure;
import br.com.erico.desafio_android.controls.util.ApiGitHub;
import br.com.erico.desafio_android.models.PullRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionRequest extends AppCompatActivity {

    private String nameAuth;
    private String nameRepository;
    private String date;
    private RecyclerView listPullRequest;
    private PullStructure structure;
    private Services gitHutServices;
    private List<PullRequest> requestList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_request);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nameAuth = getIntent().getStringExtra("authorName");
        nameRepository = getIntent().getStringExtra("repoName");

        if (nameAuth.isEmpty() || nameRepository.isEmpty()) {
            Toast.makeText(this, "Carregamento Indevido!", Toast.LENGTH_SHORT).show();
            finish();
        }

        getSupportActionBar().setTitle(nameRepository);

        progressBar = (ProgressBar) findViewById(R.id.pullProgressBar);
        gitHutServices = ApiGitHub.getGitService();
        listPullRequest = (RecyclerView) findViewById(R.id.pullList);
        structure = new PullStructure(this, new ArrayList<PullRequest>(0), new PullStructure.PullRequestListener() {
            @Override
            public void onPullRequestClick(String url) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listPullRequest.setLayoutManager(layoutManager);
        listPullRequest.setAdapter(structure);
        listPullRequest.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        listPullRequest.addItemDecoration(itemDecoration);

        if (savedInstanceState != null) {
            progressBar.setVisibility(View.GONE);
            requestList = (List<PullRequest>) savedInstanceState.getSerializable("list");
            structure.updatePullRequest(requestList);
        } else {
            loadPullRequests();
        }
    }

    public void loadPullRequests() {
        gitHutServices.getPullRequests(nameAuth, nameRepository).enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    requestList = response.body();
                    structure.updatePullRequest(requestList);
                    Log.i("DescriptionRequest", "Pull requests OK.");
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Carregamento Indevido!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Log.i("DescriptionRequest", "Erro no API!");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list", (Serializable) requestList);
    }

}
