package br.com.guilherme.concrete.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import br.com.guilherme.concrete.R;
import br.com.guilherme.concrete.adapter.PullRequestAdapter;
import br.com.guilherme.concrete.model.PullRequest;
import br.com.guilherme.concrete.presenter.PullRequestPresenter;

public class PullRequestActivity extends AppCompatActivity implements PullRequestPresenter.View {
    private CoordinatorLayout coordinatorLayout;
    private ConstraintLayout infoPROpen;
    private RecyclerView listPulls;
    private ProgressBar progressBar;
    private PullRequestPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        Toolbar toolbar = findViewById(R.id.toolbar);
        coordinatorLayout = findViewById(R.id.coordinator);
        infoPROpen = findViewById(R.id.info_pull);
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

        presenter = new PullRequestPresenter(this);
        presenter.getAllPulls(getIntent().getStringExtra("nomeUsuario"), getIntent().getStringExtra("nomeRepositorio"));
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, PullRequestActivity.class);
    }

    @Override
    public void setRecyclerView(List<PullRequest> pullRequests) {
        progressBar.setVisibility(View.GONE);
        infoPROpen.setVisibility(View.VISIBLE);
        listPulls.setVisibility(View.VISIBLE);

        TextView totalOpenPR = findViewById(R.id.abertos);
        totalOpenPR.setText(String.valueOf(pullRequests.size()).concat(" " + getResources().getString(R.string.dev_abertos)));

        PullRequestAdapter adapter = new PullRequestAdapter(pullRequests, PullRequestActivity.this, presenter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        listPulls.setLayoutManager(linearLayoutManager);
        listPulls.setAdapter(adapter);
        listPulls.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onError(String errorException) {
        progressBar.setVisibility(View.GONE);
        Snackbar.make(coordinatorLayout, errorException, Snackbar.LENGTH_LONG).show();
    }
}
