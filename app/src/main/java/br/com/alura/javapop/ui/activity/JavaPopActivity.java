package br.com.alura.javapop.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.javapop.R;
import br.com.alura.javapop.model.Repositorio;
import br.com.alura.javapop.ui.adapter.ListaJavaPopAdapter;
import br.com.alura.javapop.ui.util.EndlessRecyclerViewScrollListener;

public class JavaPopActivity extends AppCompatActivity {

    private ListaJavaPopAdapter adapter;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_pop);

        List<Repositorio> repositorios = buscarRepositórios();
        configuraRecyclerView(repositorios);
    }

    @NonNull
    private List<Repositorio> buscarRepositórios() {
        List<Repositorio> repositorios = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            Repositorio repositorio = new Repositorio("Nome Repositório "+ i, "Nome Usuário " + i);
            repositorio.setDescricao("Descrição do repositório bem grande, vamos ver como vai ficar na view com essa descrição bem grande" + i);
            repositorio.setSobrenomeUsuario("Sobrenome Usuário " + i);
            repositorio.setQuantidadeForks(i);
            repositorio.setQuantidadeEstrelas(i);
            repositorios.add(repositorio);
        }
        return repositorios;
    }

    @NonNull
    private List<Repositorio> buscarRepositórios2() {
        List<Repositorio> repositorios = new ArrayList<>();
        for(int i = 20; i < 40; i++){
            Repositorio repositorio = new Repositorio("Nome Repositório "+ i, "Nome Usuário " + i);
            repositorio.setDescricao("Descrição do repositório bem grande, vamos ver como vai ficar na view com essa descrição bem grande" + i);
            repositorio.setSobrenomeUsuario("Sobrenome Usuário " + i);
            repositorio.setQuantidadeForks(i);
            repositorio.setQuantidadeEstrelas(i);
            repositorios.add(repositorio);
        }
        return repositorios;
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
                List<Repositorio> repositorios = buscarRepositórios2();
                adapter.adiciona(repositorios);
                scrollListener.resetState();
            }
        };
    }

    private void configuraAdapter(List<Repositorio> repositorios, RecyclerView recyclerView) {
        adapter = new ListaJavaPopAdapter(this, repositorios);
        recyclerView.setAdapter(adapter);
    }


}
