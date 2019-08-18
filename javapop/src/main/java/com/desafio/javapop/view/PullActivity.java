package com.desafio.javapop.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.desafio.javapop.R;
import com.desafio.javapop.api.RetrofitCliente;
import com.desafio.javapop.lista.PullRequestAdapter;
import com.desafio.javapop.model.PullRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PullRequestAdapter pullRequestAdapter;
    String login,nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyPull);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recebeDados();
        PullTask pullTask = new PullTask();
        pullTask.execute();

    }

    private void ChamaPull(String login,String nome) {
        Call<List<PullRequest>> call = new RetrofitCliente().githubServico().getPull(login,nome);
        Log.d("url", String.valueOf(call.request().url()));
        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if (response.isSuccessful()) {
                    InseriLista(response.body());
                    Log.i("PullRequest", String.valueOf(response.body()));
                } else {
                    Log.i("Retrofit", "erro");
                }
            }
            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage() + " " + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void InseriLista(List<PullRequest> resultadoPullRequestList){
        pullRequestAdapter = new PullRequestAdapter(resultadoPullRequestList);
        recyclerView.setAdapter(pullRequestAdapter);
        pullRequestAdapter.notifyDataSetChanged();
    }

    public void recebeDados(){
        Intent intent = getIntent();
        login = intent.getStringExtra("login");
        nome = intent.getStringExtra("nome");

    }

    public class PullTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            ChamaPull(login,nome);

            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


