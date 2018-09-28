package br.com.alura.javapop.ui.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
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

import static br.com.alura.javapop.ui.constants.PRIMEIRA_PAGINA;
import static br.com.alura.javapop.ui.constants.REPOSITORIO;
import static br.com.alura.javapop.ui.constants.REQUEST_CODE;

public class JavaPopActivity extends AppCompatActivity {

    private ListaJavaPopAdapter adapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private int pagina = PRIMEIRA_PAGINA;
    private RecyclerView recyclerView;
    private List<Repositorio> repositorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_pop);

        Call<RepositorioSinc> call = new RetrofitInicializador().getAlunoService().listaRepositorios(pagina);

        call.enqueue(new Callback<RepositorioSinc>() {
            @Override
            public void onResponse(Call<RepositorioSinc> call, Response<RepositorioSinc> response) {
                RepositorioSinc repositorioSinc = response.body();
                repositorios = repositorioSinc.getRepositorios();
                configuraRecyclerView(repositorios);

                pagina++;
            }

            @Override
            public void onFailure(Call<RepositorioSinc> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(pagina != PRIMEIRA_PAGINA){
            configuraRecyclerView(repositorios);
        }
    }

    private void configuraRecyclerView(List<Repositorio> repositorios) {
        recyclerView = findViewById(R.id.java_pop_recyclerview);
        LinearLayoutManager linearLayoutManager = configuraManager(recyclerView);
        configuraAdapter(repositorios, recyclerView);
        scrollListener = configuraListaInfinita(linearLayoutManager);
        recyclerView.addOnScrollListener(scrollListener);
    }

    @NonNull
    private LinearLayoutManager configuraManager(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        return linearLayoutManager;
    }

    private void configuraAdapter(List<Repositorio> repositorios, RecyclerView recyclerView) {
        adapter = new ListaJavaPopAdapter(this, repositorios, selecionaRepositorio());
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    private ListaJavaPopAdapter.OnItemClickListener selecionaRepositorio() {
        return new ListaJavaPopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Repositorio repositorio) {
                Intent vaiParaRepositorio = new Intent(JavaPopActivity.this, RepositorioActivity.class);

                PendingIntent pendingIntent =TaskStackBuilder.create(JavaPopActivity.this)
                                .addNextIntentWithParentStack(vaiParaRepositorio)
                                .getPendingIntent(REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(JavaPopActivity.this, REPOSITORIO);
                builder.setContentIntent(pendingIntent);

                vaiParaRepositorio.putExtra(REPOSITORIO,repositorio);
                startActivity(vaiParaRepositorio);
            }
        };
    }

    private EndlessRecyclerViewScrollListener configuraListaInfinita(final LinearLayoutManager linearLayoutManager) {
        return new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                Call<RepositorioSinc> call = new RetrofitInicializador().getAlunoService().listaRepositorios(pagina);

                call.enqueue(new Callback<RepositorioSinc>() {
                    @Override
                    public void onResponse(Call<RepositorioSinc> call, Response<RepositorioSinc> response) {
                        RepositorioSinc repositorioSinc = response.body();
                        repositorios = adapter.adiciona(repositorioSinc.getRepositorios());
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


}
