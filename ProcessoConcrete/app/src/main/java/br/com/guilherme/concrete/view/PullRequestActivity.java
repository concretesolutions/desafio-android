package br.com.guilherme.concrete.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.guilherme.concrete.R;
import br.com.guilherme.concrete.adapter.PullRequestAdapter;
import br.com.guilherme.concrete.model.PullRequest;
import br.com.guilherme.concrete.presenter.PullRequestPresenter;

public class PullRequestActivity extends AppCompatActivity implements PullRequestPresenter.View {
    private RecyclerView listPulls;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorio);

        Toolbar toolbar = findViewById(R.id.toolbar);
        listPulls = findViewById(R.id.list);
        progressBar = findViewById(R.id.loading_content);

        String nomeRepositorio = getIntent().getStringExtra("nomeRepositorio") != null
                ? getIntent().getStringExtra("nomeRepositorio")
                : getResources().getString(R.string.title_padrao_reqeuests);
        toolbar.setTitle(nomeRepositorio);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        PullRequestPresenter presenter = new PullRequestPresenter(this);
        presenter.getAllPulls();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, PullRequestActivity.class);
    }

    @Override
    public void setRecyclerView(List<PullRequest> pullRequests) {
        progressBar.setVisibility(View.GONE);
        listPulls.setVisibility(View.VISIBLE);

        PullRequestAdapter adapter = new PullRequestAdapter(pullRequests, PullRequestActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        listPulls.setLayoutManager(linearLayoutManager);
        listPulls.setAdapter(adapter);
        listPulls.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onError(String errorException) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), errorException, Toast.LENGTH_LONG).show();
    }
}
