package com.desafioconcret;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.desafioconcret.adapters.GitAdapter;
import com.desafioconcret.net.GitHubApiService;
import com.desafioconcret.pojo.json.Repositories;
import com.desafioconcret.pojo.json.TopRepositorios;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    /**
     * Criação do Builder do Gson, Retrofit e OkHttp
     **/

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private Integer page = 1;
    private EndlessScroll endlessScroll;
    private GitAdapter adapter;
    GitHubApiService gitHubApiService = new GitHubApiService();

    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.layoutManager = new LinearLayoutManager(this);

        this.recyclerView = findViewById(R.id.repos_recicle_view);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.addItemDecoration(new SimpleDivider(this));

        toolbar = findViewById(R.id.toolbar_pullrequest);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        mostraListaRepos();

        endlessScroll = new EndlessScroll((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                carregaNovaPaginaApi(page);
            }
        };
        recyclerView.addOnScrollListener(endlessScroll);
    }

    public void carregaNovaPaginaApi(int offset) {

        if (page >= 1) {
            page = page + 1;
            mostraListaRepos();
        } else {
        }
    }

    private void mostraListaRepos() {

        Observable<TopRepositorios> repositoriosObservable = gitHubApiService
                .topRepositorios().getTopRepositorios("language:Java", "stars", page);

        repositoriosObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TopRepositorios>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(TopRepositorios topRepositorios) {

                        List<Repositories> repositorios = topRepositorios.getRepositories();
                        adapter = new GitAdapter(repositorios);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("conexção", "Concexão erro" + e.toString());

                    }

                    @Override
                    public void onComplete() {
                    }
                });


    }
            @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.repo_exit:
                    finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }


}





