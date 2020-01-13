package br.com.josef.desafioconcretegit.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.josef.desafioconcretegit.R;
import br.com.josef.desafioconcretegit.model.pojo.pull.PullRequest;
import br.com.josef.desafioconcretegit.view.adapter.PullRequestAdapter;
import br.com.josef.desafioconcretegit.view.interfaces.PullRequestOnclick;
import br.com.josef.desafioconcretegit.viewmodel.RepositorioViewModel;

import static br.com.josef.desafioconcretegit.view.activity.MainActivity.LOGIN_ID;
import static br.com.josef.desafioconcretegit.view.activity.MainActivity.REPOSITORY_ID;


public class PullRequestActivity extends AppCompatActivity implements PullRequestOnclick {

    private TextView titulo;
    private TextView descricao;
    private TextView username;
    private TextView nomeSobrenome;

    private PullRequest pullRequest;
    private List<PullRequest> pullRequestList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PullRequestAdapter adapter;
    private RepositorioViewModel viewModel;
    private ProgressBar progressBar;
    private int page = 1;
    private String login;
    private String repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);
        initViews();

        Intent intent = getIntent();


        Bundle bundle = intent.getExtras();
        login = bundle.getString(LOGIN_ID);
        repository = bundle.getString(REPOSITORY_ID);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));


        viewModel.getPullRequest(login, repository);
        viewModel.getRequest().observe(this, requestList -> {
            adapter.atualizaLista(requestList);
        });

        viewModel.getBooleano().observe(this, aBoolean -> {
            if (aBoolean) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    public void initViews() {
        titulo = findViewById(R.id.txtTituloPull);
        descricao = findViewById(R.id.txtDescPull);
        username = findViewById(R.id.txtUserPull);
        nomeSobrenome = findViewById(R.id.txtNomeSobrenome);
        viewModel = ViewModelProviders.of(this).get(RepositorioViewModel.class);
        adapter = new PullRequestAdapter(pullRequestList, this);
        recyclerView = findViewById(R.id.RecyclerPullRequest);
        progressBar = findViewById(R.id.progress_bar);

    }



    @Override
    public void OnClick(PullRequest pullRequest) {
        String url = pullRequest.getHtmlUrl();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


}