package br.com.alura.javapop.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.com.alura.javapop.R;
import br.com.alura.javapop.dto.RepositorioSinc;
import br.com.alura.javapop.model.PullRequest;
import br.com.alura.javapop.model.Repositorio;
import br.com.alura.javapop.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.alura.javapop.ui.constants.REPOSITORIO;

public class RepositorioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorio);

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
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());

            }

        });

    }
}
