package br.com.githubjavapop.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import br.com.githubjavapop.R;
import br.com.githubjavapop.adapter.PullRequestAdapter;
import br.com.githubjavapop.entidade.PullRequest;
import br.com.githubjavapop.service.PullRequestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PullRequestActivity extends AppCompatActivity {

    private static String URL;
    private static String REPOSITORIO;
    private static String USERNAME;

    private List<PullRequest> listaPullRequest;
    private PullRequestAdapter pullRequestAdapter;
    private RecyclerView recyclerPullRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);

        ImageView imagemVoltar;

        imagemVoltar = (ImageView) findViewById(R.id.imagemVoltar);

        imagemVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PullRequestActivity.this, MainActivity.class));
            }
        });

        URL = getIntent().getStringExtra("url");
        REPOSITORIO = getIntent().getStringExtra("repositorio");
        USERNAME = getIntent().getStringExtra("username");

        recyclerPullRequest = (RecyclerView) findViewById(R.id.rccPull);
        recyclerPullRequest.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        obterPulls();
    }

    private void obterPulls() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        PullRequestService pullRequestService = retrofit.create(PullRequestService.class);
        Call<List<PullRequest>> pulls = pullRequestService.getPullRequest(USERNAME, REPOSITORIO);

        pulls.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if(response.isSuccessful()) listaPullRequest = response.body();

                pullRequestAdapter = new PullRequestAdapter(listaPullRequest);

                pullRequestAdapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browseIt = new Intent(Intent.ACTION_VIEW, Uri.parse(listaPullRequest
                                .get(recyclerPullRequest.getChildAdapterPosition(v)).getHtmlURL()));
                        startActivity(browseIt);
                    }
                });

                recyclerPullRequest.setAdapter(pullRequestAdapter);
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                Toast.makeText(PullRequestActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}