package com.example.gitbusca.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gitbusca.R;
import com.example.gitbusca.adapter.AdapterPulls;
import com.example.gitbusca.callback.PullCallback;
import com.example.gitbusca.model.Pull;
import com.example.gitbusca.model.RepositorioGit;
import com.example.gitbusca.model.User;
import com.example.gitbusca.service.GitHubService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PullActivity extends AppCompatActivity {

    private RepositorioGit repositorioAtual;
    private ProgressBar progressPull;
    private RecyclerView recyclerViewPulls;
    private List<Pull> listaPulls = new ArrayList<Pull>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        recyclerViewPulls = findViewById(R.id.recyclerPull);
        progressPull = findViewById(R.id.progressPull);
        repositorioAtual = (RepositorioGit) getIntent().getSerializableExtra("repositorioSelecionado");

        Retrofit retrofit = new Retrofit.Builder().baseUrl(
                GitHubService.BASE_URL + GitHubService.REPOS + repositorioAtual.getFull_name() + "/" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Pull>> request = service.listarPulls();
        request.enqueue( new PullCallback(this));
    }

    public void carregarRecycle(){
        //Configurar Adapter
        AdapterPulls adapter = new AdapterPulls( listaPulls, this );

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPulls.setLayoutManager( layoutManager );
        recyclerViewPulls.setHasFixedSize(true);
        recyclerViewPulls.addItemDecoration( new DividerItemDecoration(PullActivity.this, LinearLayout.VERTICAL) );
        recyclerViewPulls.setAdapter( adapter );

        progressPull.setVisibility( View.GONE );

        if (listaPulls.size() < 1){
            Toast.makeText(this, "Esse repositório não possui pull request!", Toast.LENGTH_SHORT).show();
        }
    }

    public void inserirListaPull(String title, int number, String body, User user){
        Pull p1 = new Pull(title, number, body, user);
        listaPulls.add( p1 );
    }
}
