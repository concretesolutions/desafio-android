package br.com.concrete.desafio_android.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.concrete.desafio_android.R;
import br.com.concrete.desafio_android.domain.Repository;
import br.com.concrete.desafio_android.gui.adapter.RepositoryAdapter;
import br.com.concrete.desafio_android.gui.listener.EndlessScrollListener;
import br.com.concrete.desafio_android.presenter.RepositoryPresenter;

import static br.com.concrete.desafio_android.util.Constants.JAVA;
import static br.com.concrete.desafio_android.util.Constants.JAVA_SCRIPT;
import static br.com.concrete.desafio_android.util.Constants.LIST_VIEW_INSTANCE_STATE_KEY;
import static br.com.concrete.desafio_android.util.Constants.PHP;
import static br.com.concrete.desafio_android.util.Constants.PYTHON;
import static br.com.concrete.desafio_android.util.Constants.REPOSITORY;

public class RepositoryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener{

    private ListView repositoryListView = null;
    private RepositoryAdapter repositoryAdapter = null;
    private ArrayList<Repository> repositories;
    private Bundle arguments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        repositoryListView = findViewById(R.id.repositoryListView);
        repositories = new ArrayList<Repository>();
        repositoryAdapter = new RepositoryAdapter(R.layout.repository_item, RepositoryActivity.this, repositories);
        repositoryListView.setAdapter(repositoryAdapter);
        repositoryListView.setOnItemClickListener(RepositoryActivity.this);
        repositoryListView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                RepositoryPresenter.getInstance().loadMore(page);
                return false;
            }
        });

        RepositoryPresenter.getInstance().initializeData(savedInstanceState,JAVA,RepositoryActivity.this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LIST_VIEW_INSTANCE_STATE_KEY, repositories);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent pullRequest = new Intent(RepositoryActivity.this, PullActivity.class);
        pullRequest.putExtra(REPOSITORY,repositories.get(i));
        startActivity(pullRequest);
    }

    public ListView getRepositoryListView() {
        return repositoryListView;
    }

    public void setRepositoryListView(ListView repositoryListView) {
        this.repositoryListView = repositoryListView;
    }

    public RepositoryAdapter getRepositoryAdapter() {
        return repositoryAdapter;
    }

    public void setRepositoryAdapter(RepositoryAdapter repositoryAdapter) {
        this.repositoryAdapter = repositoryAdapter;
    }

    public ArrayList<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(ArrayList<Repository> repositories) {
        this.repositories = repositories;
    }

    public Bundle getArguments() {
        return arguments;
    }

    public void setArguments(Bundle arguments) {
        this.arguments = arguments;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.java) {
            RepositoryPresenter.getInstance().reloadWithNewData(JAVA);
        } else if (id == R.id.python) {
            RepositoryPresenter.getInstance().reloadWithNewData(PYTHON);
        } else if (id == R.id.php) {
            RepositoryPresenter.getInstance().reloadWithNewData(PHP);
        } else if (id == R.id.javascript) {
            RepositoryPresenter.getInstance().reloadWithNewData(JAVA_SCRIPT);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
