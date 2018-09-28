package br.com.alura.javapop.ui.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.com.alura.javapop.R;
import br.com.alura.javapop.model.PullRequest;
import br.com.alura.javapop.model.Repositorio;
import br.com.alura.javapop.retrofit.RetrofitInicializador;
import br.com.alura.javapop.ui.adapter.ListaJavaPopAdapter;
import br.com.alura.javapop.ui.adapter.ListaPullRequestAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.alura.javapop.ui.constants.REPOSITORIO;
import static br.com.alura.javapop.ui.constants.REQUEST_CODE;

public class PullRequestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaPullRequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        Repositorio repositorio = (Repositorio) getIntent().getSerializableExtra(REPOSITORIO);

        String nomeRepositorio = repositorio.getNome();
        String nomeUsuario = repositorio.getUsuario().getNome();

        setTitle(nomeRepositorio);

        Call<List<PullRequest>> call = new RetrofitInicializador().getAlunoService()
                .listaPullRequest(nomeUsuario,nomeRepositorio);

        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>>call, Response<List<PullRequest>> response) {
                List<PullRequest> pullRequests = response.body();
                configurarRecyclerView(pullRequests);
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());

            }

        });


    }

    private void configurarRecyclerView(List<PullRequest> pullRequests) {
        recyclerView = findViewById(R.id.pull_request_recyclerview);
        LinearLayoutManager manager = configurarManager(recyclerView);
        configuraAdapter(pullRequests, recyclerView);
    }

    private LinearLayoutManager configurarManager(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        return linearLayoutManager;
    }

    private void configuraAdapter(List<PullRequest> pullRequests, RecyclerView recyclerView) {
        adapter = new ListaPullRequestAdapter(this, pullRequests, selecionaPullRequest());
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    private ListaPullRequestAdapter.OnItemClickListener selecionaPullRequest() {
        return new ListaPullRequestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PullRequest pullRequest) {
                Intent abrirNavegador = new Intent(Intent.ACTION_VIEW);
                abrirNavegador.setData(Uri.parse(pullRequest.getUrl()));
                startActivity(abrirNavegador);
            }
        };
    }
}
