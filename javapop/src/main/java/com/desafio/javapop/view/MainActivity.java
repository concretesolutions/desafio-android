package com.desafio.javapop.view;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.desafio.javapop.R;
import com.desafio.javapop.api.RetrofitCliente;
import com.desafio.javapop.lista.RepositorioAdapter;
import com.desafio.javapop.model.Repositorio;
import com.desafio.javapop.model.ResultadoRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RepositorioAdapter repositorioAdapter;
    private LinearLayoutManager linearLayoutManager;

    private static String q = "language:Java";
    private static String sort = "stars";
    private int pagina = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conexao();
        recyclerView = (RecyclerView) findViewById(R.id.recy);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        RepoTask repoTask = new RepoTask();
        repoTask.execute();
        contaPagina();
    }

    private void ChamaRepositorio(int page) {
        Call<ResultadoRepo> call = new RetrofitCliente().githubServico().getRepositorio(q, sort, page);
        Log.i("url", String.valueOf(call.request().url()));
        call.enqueue(new Callback<ResultadoRepo>() {
            @Override
            public void onResponse(Call<ResultadoRepo> call, Response<ResultadoRepo> response) {
                if (response.isSuccessful()) {
                    InseriRepo(response.body().getRepositorios());

                    Log.i("Retrofit", String.valueOf(response.body().getRepositorios()));
                } else {

                    Log.i("Retrofit", "erro");

                }
            }

            @Override
            public void onFailure(Call<ResultadoRepo> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage() + " " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void InseriRepo(List<Repositorio> repositorios) {
        repositorioAdapter = new RepositorioAdapter(repositorios, getBaseContext());
        recyclerView.setAdapter(repositorioAdapter);
        repositorioAdapter.notifyDataSetChanged();

    }

    public class RepoTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ChamaRepositorio(pagina);
            return null;
        }
    }

    public void contaPagina() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int QtdVisivel = linearLayoutManager.getChildCount();
                int QtdTotalItem = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                if ((QtdVisivel + firstVisibleItemPosition) >= QtdTotalItem) {
                    pagina++;
                    ChamaRepositorio(pagina);
                }
                Log.i("pg", dy + " vertical " + "QtdVisivel " + QtdVisivel
                        + " QtdTotalItem " + QtdTotalItem
                        + " firstVisibleItemPosition " + firstVisibleItemPosition
                        + "pagina " + pagina);
            }
        });
    }

    public boolean conexao() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }else{
            Toast.makeText(getBaseContext(),"Sem Conexão",Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
