package br.com.desafio.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import br.com.desafio.R;
import br.com.desafio.common.Connection;
import br.com.desafio.common.Load;
import br.com.desafio.common.State;
import br.com.desafio.controller.RepositoryController;
import br.com.desafio.domain.PullRequest;
import br.com.desafio.domain.Repositories;
import br.com.desafio.domain.Repository;
import br.com.desafio.ui.adapter.RepositoryAdapter;

@EActivity(R.layout.repository_activity)
public class RepositoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    @InstanceState
    Repositories repositories;
    @Bean
    RepositoryController repositoryController;
    private final String language = "java";
    @InstanceState
    Integer page;
    @ViewById(R.id.repostories)
    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    @Bean
    RepositoryAdapter repositoryAdapter;
    @InstanceState
    Integer lastItemClicked;
    Load load;
    @InstanceState
    State estado;
    private Integer maxPage = 35;
    private final int perPage = 30;
    @StringRes(R.string.without_connection)
    String semConexao;
    @StringRes(R.string.github)
    String titleActionBar;
    @StringRes(R.string.without_further_repositories)
    String semRepositorios;

    //https://bitbucket.org/suporte_concrete/desafio-android

    @AfterViews
    void afterViews(){
        EventBus.getDefault().register(this);
        this.load = new Load(this);

        //getSupportActionBar().setTitle(titleActionBar);

        if(estado == null)
            this.estado = State.INITIAL;

        if(page == null)
            this.page = 1;

        if(repositories == null){
            if (Connection.isOnline(this)) {
                this.repositoryController.findRepository(page, language);
                this.load.show();
            } else {
                Toast.makeText(this, semConexao, Toast.LENGTH_SHORT).show();
            }            
        }else{
            this.initListView();
        }

        this.layoutManager = new LinearLayoutManager(recyclerView.getContext());
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();

                if (lastVisibleItem + 1 == totalItemCount && (estado == State.INITIAL || estado == State.FINISHED)) {
                    if(page <= maxPage && estado != State.NO_MORE_REPOSITORIES){
                        RepositoryActivity.this.onRefresh();
                    }else{
                        RepositoryActivity.this.estado = State.NO_MORE_REPOSITORIES;
                        Toast.makeText(RepositoryActivity.this, semRepositorios, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWebClientRepository(final Repositories repositories){
        this.page += 1;
        this.load.hide();

        if(this.estado == State.INITIAL){
            this.repositories = repositories;
            this.initListView();
        }else{
            double extra = repositories.getTotalCount() % perPage;
            maxPage = (repositories.getTotalCount() / perPage) + (extra > 0d? 1: 0);

            for (Repository repository: repositories.getItems()){
                this.repositoryAdapter.addItem(repository, this.repositoryAdapter.getItemCount());
            }
        }

        this.estado = State.FINISHED;
    }

    void initListView(){
        this.repositoryAdapter.setItems(repositories);
        this.repositoryAdapter.setOnItemClickListener(this);
        this.recyclerView.setAdapter(repositoryAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(Connection.isOnline(this)) {
            ArrayList<PullRequest> pullRequesties = (ArrayList<PullRequest>) repositories.getItems().get(position).getPullRequests();
            if(pullRequesties != null) {
                PullRequestActivity_.intent(RepositoryActivity.this)
                    .extra("owner", repositories.getItems().get(position).getOwner().getName())
                    .extra("pullRequesties", pullRequesties)
                    .extra("repository", repositories.getItems().get(position).getName())
                    .start();
            }else{
                PullRequestActivity_.intent(RepositoryActivity.this)
                    .extra("owner", repositories.getItems().get(position).getOwner().getName())
                    .extra("repository", repositories.getItems().get(position).getName())
                    .start();
            }

            this.lastItemClicked = position;
        }else{
            Toast.makeText(this, semConexao, Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWebClientError(final String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        this.estado = State.FINISHED;
        this.load.hide();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onWebClientPullRequest(List<PullRequest> pullRequesties){
        if(lastItemClicked != null)
            repositories.getItems().get(lastItemClicked).setPullRequests(pullRequesties);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onRefresh() {
        if (Connection.isOnline(RepositoryActivity.this)) {
            this.estado = State.AWAITING;
            RepositoryActivity.this.repositoryController.findRepository(page, language);
            this.load.show();
        } else {
            Toast.makeText(RepositoryActivity.this, semConexao, Toast.LENGTH_SHORT).show();
        }
    }
}