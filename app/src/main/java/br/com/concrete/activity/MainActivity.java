package br.com.concrete.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import br.com.concrete.R;
import br.com.concrete.adapter.MainAdapter;
import br.com.concrete.base.BaseDrawerActivity;
import br.com.concrete.model.RetornoRepositorio;
import br.com.concrete.util.Constantes;
import br.com.concrete.util.EndlessRecyclerViewScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseDrawerActivity {

    // Views
    private RecyclerView list_repos;

    // Utils
    private MainAdapter mainAdapter;
    private LinearLayoutManager layoutManager;
    private EndlessRecyclerViewScrollListener scrollListener;
    private List<RetornoRepositorio.Repositorio> repos = new ArrayList<>();
    private final String LIST_STATE_KEY = "recycler_state";
    private static Bundle mBundleRecyclerViewState;

    public MainActivity(){
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main);
        onShowProgressDialog(Constantes.AGUARDE);
        init();
        getFeeds("1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = list_repos.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(LIST_STATE_KEY, listState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(LIST_STATE_KEY);
            list_repos.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    private void init(){
        context = this;
        list_repos = findViewById(R.id.list_repos);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        list_repos.setLayoutManager(layoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(page + 1);
            }
        };
        list_repos.addOnScrollListener(scrollListener);
    }

    private void getFeeds(final String page) {
        Call<RetornoRepositorio> call = restApi.getRepositorios(page);
        call.enqueue(new Callback<RetornoRepositorio>() {
            @Override
            public void onResponse(Call<RetornoRepositorio> call, Response<RetornoRepositorio> response) {
                onDismissProgressDialog();
                if (response.body() != null && response.body().getItems() != null && !response.body().getItems().isEmpty()) {
                    if(repos.isEmpty()){
                        repos.addAll(response.body().getItems());
                    } else {
                        for(RetornoRepositorio.Repositorio repo : response.body().getItems()){
                            repos.add(repo);
                        }
                    }
                    populateList(repos);
                } else {
                    Log.e("Error ","Não há repositórios");
                }
            }

            @Override
            public void onFailure(Call<RetornoRepositorio> call, Throwable t) {
                onDismissProgressDialog();
                Log.e("Deu ruim time line", t.getMessage());
                new AlertDialog.Builder(context).setMessage("Erro ao buscar repositórios! Tente novamente mais tarde...").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                }).show();
            }
        });
    }

    private void loadNextDataFromApi(int page) {
        getFeeds(String.valueOf(page));
    }

    private void populateList(List<RetornoRepositorio.Repositorio> listaRepos){
        if(mainAdapter == null){
            mainAdapter = new MainAdapter(this, listaRepos);
            list_repos.setAdapter(mainAdapter);
        } else {
            mainAdapter.notifyDataSetChanged();
        }
    }
}