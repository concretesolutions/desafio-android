package com.example.gitbusca.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.gitbusca.R;
import com.example.gitbusca.adapter.AdapterRepositoriosGit;
import com.example.gitbusca.callback.GitCatalogoCallback;
import com.example.gitbusca.helper.RecyclerItemClickListener;
import com.example.gitbusca.model.GitCatalogo;
import com.example.gitbusca.model.RepositorioGit;
import com.example.gitbusca.service.GitHubService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerRepositorios;
    private ProgressBar progressRepo;
    private List<RepositorioGit> lista = new ArrayList<RepositorioGit>();

    public final String QUERY = "language:Java";
    public final String SORT = "stars";
    public final int PAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerRepositorios = findViewById(R.id.recyclerRepositorios);
        progressRepo = findViewById(R.id.progressRep);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(GitHubService.BASE_URL)
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<GitCatalogo> request = service.listarCatalogo(QUERY, SORT, PAGE);
        request.enqueue( new GitCatalogoCallback(this) );

    }

    public void carregarRecycle(){
        //Configurar Adapter
        AdapterRepositoriosGit adapterepositoriosGit = new AdapterRepositoriosGit( lista, this );

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerRepositorios.setLayoutManager( layoutManager );
        recyclerRepositorios.setHasFixedSize(true);
        recyclerRepositorios.addItemDecoration( new DividerItemDecoration(MainActivity.this, LinearLayout.VERTICAL) );
        recyclerRepositorios.setAdapter( adapterepositoriosGit );

        recyclerRepositorios.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                recyclerRepositorios,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        RepositorioGit repositorioSelecionado = lista.get( position );
                        Intent intent = new Intent(MainActivity.this, PullActivity.class);
                        intent.putExtra("repositorioSelecionado", repositorioSelecionado);

                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));

        progressRepo.setVisibility(View.GONE);
    }

    public void inserirListaRepositorio(long idRepositorio, String name, String description, String login, String fullName, int stars, int forks, String avatar_url){
        RepositorioGit rep = new RepositorioGit(idRepositorio, name, description, login, fullName, stars, forks, avatar_url);
        lista.add( rep );
    }
}
