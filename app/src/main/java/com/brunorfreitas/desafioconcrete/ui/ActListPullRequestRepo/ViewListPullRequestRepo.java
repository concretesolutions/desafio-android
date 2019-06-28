package com.brunorfreitas.desafioconcrete.ui.ActListPullRequestRepo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brunorfreitas.desafioconcrete.R;
import com.brunorfreitas.desafioconcrete.data.Model.Item;
import com.brunorfreitas.desafioconcrete.data.Model.PullRequestRepositoryResponse;
import com.brunorfreitas.desafioconcrete.ui.ActPullRequest.ViewPullRequest;
import com.brunorfreitas.desafioconcrete.ui.Adapter.AdapterPullRequestRepo;

import java.util.ArrayList;
import java.util.List;

public class ViewListPullRequestRepo extends AppCompatActivity implements ContractListPullRequestRepo.View {

    private ContractListPullRequestRepo.Presenter presenter;
    private Context context;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<PullRequestRepositoryResponse> pullRequestRepoList;
    private AdapterPullRequestRepo adapterPullRequestRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pull_request_repo_list);

        inicializaVariaves();
        inicializaAcoes();
    }

    private void inicializaVariaves() {
        context = getBaseContext();

        //
        progressBar = findViewById(R.id.act_pull_request_repo_list_pb);
        recyclerView = findViewById(R.id.act_pull_request_repo_list_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        pullRequestRepoList = new ArrayList<>();

        String owner = getIntent().getStringExtra("owner");
        String repo = getIntent().getStringExtra("repo");
        presenter = new PresenterListPullRequestRepo(this);
        presenter.loadListPullRequestRepo(owner, repo);
        adapterPullRequestRepo = new AdapterPullRequestRepo(context, pullRequestRepoList, owner, repo);
        recyclerView.setAdapter(adapterPullRequestRepo);
        //
        Toolbar toolbar = findViewById(R.id.act_pull_request_repo_list_tb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(repo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void inicializaAcoes() {

        adapterPullRequestRepo.setI_adapterPullRequestRepo(new AdapterPullRequestRepo.I_AdapterPullRequestRepo() {
            @Override
            public void onClickPullRequestRepo(int position) {
                String urlPullRequest = pullRequestRepoList.get(position).getHtmlUrl();
                String title = pullRequestRepoList.get(position).getTitle();

                Intent intent = new Intent(context, ViewPullRequest.class);
                intent.putExtra("url", urlPullRequest);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showPullRequestRepoList(List<PullRequestRepositoryResponse> repoList) {
        pullRequestRepoList = repoList ;
        adapterPullRequestRepo.addItens(repoList);

        TextView tv_semResultado = findViewById(R.id.act_pull_request_repo_list_tv_sem_resultado);
        if (repoList.size()==0) {
            tv_semResultado.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
