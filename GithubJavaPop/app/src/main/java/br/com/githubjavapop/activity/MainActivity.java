package br.com.githubjavapop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import br.com.githubjavapop.R;
import br.com.githubjavapop.adapter.ListaRepositorioAdapter;
import br.com.githubjavapop.entidade.ListaRepositorio;
import br.com.githubjavapop.service.ListaRepositorioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://api.github.com";
    private static final String LANGUAGE = "language";
    private static final String SORT = "stars";
    private static final int page = 1;

    private RecyclerView recyclerRepositorio;
    private ListaRepositorio listaRepositorio;
    private ListaRepositorioAdapter listaRepositorioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerRepositorio = (RecyclerView) findViewById(R.id.rccRepo);
        recyclerRepositorio.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        obterRepos(page);
    }

    public void obterRepos(int page) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        ListaRepositorioService repositorioService = retrofit.create(ListaRepositorioService.class);

            Call<ListaRepositorio> repos = repositorioService.getListaRepositorio(LANGUAGE, SORT, page);

            repos.enqueue(new Callback<ListaRepositorio>() {
                @Override
                public void onResponse(Call<ListaRepositorio> call, Response<ListaRepositorio> response) {
                    if (response.isSuccessful()) listaRepositorio = response.body();

                    listaRepositorioAdapter = new ListaRepositorioAdapter(listaRepositorio);

                    listaRepositorioAdapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent it = new Intent(MainActivity.this, PullRequestActivity.class);
                            it.putExtra("url", URL);
                            it.putExtra("repositorio", listaRepositorio.getListaRepositorio().get(recyclerRepositorio
                                    .getChildAdapterPosition(v)).getNomeRepositorio());
                            it.putExtra("username", listaRepositorio.getListaRepositorio().get(recyclerRepositorio
                                    .getChildAdapterPosition(v)).getUsuario().getUsername());
                            startActivity(it);
                        }
                    });

                    recyclerRepositorio.setAdapter(listaRepositorioAdapter);
                }

                @Override
                public void onFailure(Call<ListaRepositorio> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

    }
}