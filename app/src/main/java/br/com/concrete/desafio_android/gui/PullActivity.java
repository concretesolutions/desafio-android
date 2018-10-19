package br.com.concrete.desafio_android.gui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.concrete.desafio_android.R;
import br.com.concrete.desafio_android.domain.Pull;
import br.com.concrete.desafio_android.domain.Repository;
import br.com.concrete.desafio_android.gui.adapter.PullAdapter;
import br.com.concrete.desafio_android.presenter.PullPresenter;

import static br.com.concrete.desafio_android.util.Constants.PULL_LIST_VIEW_INSTANCE_STATE_KEY;
import static br.com.concrete.desafio_android.util.Constants.REPOSITORY;

public class PullActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView pullListView = null;
    private PullAdapter pullAdapter = null;
    private ArrayList<Pull> pullRequests;
    private TextView open, close, repositoryName;
    private ImageView imageView;
    private Bundle arguments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pullListView = findViewById(R.id.pullRequestListView);
        pullRequests = new ArrayList<Pull>();
        pullAdapter = new PullAdapter(R.layout.pull_item, PullActivity.this, pullRequests);
        pullListView.setAdapter(pullAdapter);
        pullListView.setOnItemClickListener(PullActivity.this);
        open = findViewById(R.id.pull_request_open);
        close = findViewById(R.id.pull_request_close);
        repositoryName = findViewById(R.id.repository_name);
        imageView = findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Repository repository = (Repository) getIntent().getSerializableExtra(REPOSITORY);

        PullPresenter.getInstance().initializeData(PullActivity.this, repository, savedInstanceState);
    }

    public Bundle getArguments() {
        return arguments;
    }

    public void setArguments(Bundle arguments) {
        this.arguments = arguments;
    }

    public TextView getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(TextView repositoryName) {
        this.repositoryName = repositoryName;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getOpen() {
        return open;
    }

    public void setOpen(TextView open) {
        this.open = open;
    }

    public TextView getClose() {
        return close;
    }

    public void setClose(TextView close) {
        this.close = close;
    }

    public ListView getPullListView() {
        return pullListView;
    }

    public void setPullListView(ListView pullListView) {
        this.pullListView = pullListView;
    }

    public PullAdapter getPullAdapter() {
        return pullAdapter;
    }

    public void setPullAdapter(PullAdapter pullAdapter) {
        this.pullAdapter = pullAdapter;
    }

    public ArrayList<Pull> getPullRequests() {
        return pullRequests;
    }

    public void setPullRequests(ArrayList<Pull> pullRequests) {
        this.pullRequests = pullRequests;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent browser = new Intent(Intent.ACTION_VIEW);
        browser.setData(Uri.parse(pullRequests.get(i).getHtml_url()));
        startActivity(browser);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PULL_LIST_VIEW_INSTANCE_STATE_KEY, pullRequests);
    }

}
