package com.brunorfreitas.desafioconcrete.ui.ActListRepository;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.brunorfreitas.desafioconcrete.R;
import com.brunorfreitas.desafioconcrete.data.Model.Item;
import com.brunorfreitas.desafioconcrete.ui.ActListPullRequestRepo.ViewListPullRequestRepo;
import com.brunorfreitas.desafioconcrete.ui.Adapter.AdapterListRepo;

import java.util.ArrayList;
import java.util.List;

public class ViewListRepository extends AppCompatActivity implements ContractListRepository.View,
        NavigationView.OnNavigationItemSelectedListener{

    private Context context;
    private ContractListRepository.Presenter presenter;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<Item> repoList;
    private AdapterListRepo adapterListRepo;
    private DrawerLayout drawerLayout;
    //
    private int currentItens, totalItens, scrollOutItens;
    private boolean isScrolling = false;
    private LinearLayoutManager manager_rv;
    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_repo);

        inicializaVariaves();
        inicializaAcoes();
    }

    private void inicializaVariaves() {
        context = getBaseContext();
        //
        toolbar = findViewById(R.id.act_list_repo_tb);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.act_list_repo_navView);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.act_list_repo_drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_aberto,
                R.string.drawer_fechado
        );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //
        progressBar = findViewById(R.id.act_list_repo_pb);
        recyclerView = findViewById(R.id.act_list_repo_rv);
        recyclerView.setHasFixedSize(true);
        manager_rv = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager_rv);
        repoList = new ArrayList<>();
        adapterListRepo = new AdapterListRepo(context, repoList);
        recyclerView.setAdapter(adapterListRepo);
        //
        presenter = new PresenterListRepository(this);
        presenter.loadListRepo(page);
    }

    private void inicializaAcoes() {

        adapterListRepo.setI_adapterListRepo(new AdapterListRepo.I_AdapterListRepo() {
            @Override
            public void onClickRepo(int position) {
                Item item = repoList.get(position);
                String owner = item.getOwner().getLogin();
                String repo = item.getName();
                openListPullRequestRepo(owner, repo);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItens = manager_rv.getChildCount();
                totalItens = manager_rv.getItemCount();
                scrollOutItens = manager_rv.findFirstVisibleItemPosition();

                if(isScrolling &&  (currentItens  + scrollOutItens == totalItens )){
                    isScrolling = false;

                    int nextPage = page++;
                    presenter.loadListRepo(page);

                }
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
    public void showRepoList(List<Item> repoList) {
        this.repoList = repoList;
        adapterListRepo.addItens(repoList);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openListPullRequestRepo(String owner, String repo) {

//        Toast.makeText(context, "abrir outra tela", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, ViewListPullRequestRepo.class);
        intent.putExtra("owner", owner);
        intent.putExtra("repo", repo);
        startActivity(intent);
    }

    @Override
    public void addListRepo(List<Item> items) {
        this.repoList.addAll(items);
        adapterListRepo.addMoreItems(items);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_drawer_nav_home:
                Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_drawer_nav_configuracoes:
                Toast.makeText(context, "Config", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_drawer_nav_sair:
                Toast.makeText(context, "Sair", Toast.LENGTH_SHORT).show();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
