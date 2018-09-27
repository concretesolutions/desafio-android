package br.com.alura.javapop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import br.com.alura.javapop.R;
import br.com.alura.javapop.dto.RepositorioSinc;
import br.com.alura.javapop.model.Repositorio;
import br.com.alura.javapop.retrofit.RetrofitInicializador;
import br.com.alura.javapop.ui.adapter.ListaJavaPopAdapter;
import br.com.alura.javapop.ui.util.EndlessRecyclerViewScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JavaPopActivity extends AppCompatActivity {

    private ListaJavaPopAdapter adapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private int pagina = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_pop);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Call<RepositorioSinc> call = new RetrofitInicializador().getAlunoService().lista(pagina);

        call.enqueue(new Callback<RepositorioSinc>() {
            @Override
            public void onResponse(Call<RepositorioSinc> call, Response<RepositorioSinc> response) {
                RepositorioSinc repositorioSinc = response.body();
                configuraRecyclerView(repositorioSinc.getRepositorios());
                pagina++;
            }

            @Override
            public void onFailure(Call<RepositorioSinc> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());
            }
        });

    }

    private void configuraRecyclerView(List<Repositorio> repositorios) {
        RecyclerView recyclerView = findViewById(R.id.java_pop_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        configuraAdapter(repositorios, recyclerView);
        scrollListener = configuraListaInfinita(linearLayoutManager);
        recyclerView.addOnScrollListener(scrollListener);
    }

    private EndlessRecyclerViewScrollListener configuraListaInfinita(final LinearLayoutManager linearLayoutManager) {
        return new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                Call<RepositorioSinc> call = new RetrofitInicializador().getAlunoService().lista(pagina);

                call.enqueue(new Callback<RepositorioSinc>() {
                    @Override
                    public void onResponse(Call<RepositorioSinc> call, Response<RepositorioSinc> response) {
                        RepositorioSinc repositorioSinc = response.body();
                        adapter.adiciona(repositorioSinc.getRepositorios());
                        scrollListener.resetState();
                        pagina++;
                    }

                    @Override
                    public void onFailure(Call<RepositorioSinc> call, Throwable t) {
                        Log.e("onFailure chamado", t.getMessage());
                    }
                });
            }
        };
    }

    private void configuraAdapter(List<Repositorio> repositorios, RecyclerView recyclerView) {
        adapter = new ListaJavaPopAdapter(this, repositorios);
        recyclerView.setAdapter(adapter);
    }


}
