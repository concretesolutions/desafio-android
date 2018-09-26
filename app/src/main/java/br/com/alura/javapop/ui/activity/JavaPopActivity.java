package br.com.alura.javapop.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.javapop.R;
import br.com.alura.javapop.model.Repositorio;
import br.com.alura.javapop.ui.adapter.ListaJavaPopAdapter;

public class JavaPopActivity extends AppCompatActivity {

    private ListaJavaPopAdapter adapter;

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
            Repositorio repositorio = new Repositorio("Nome Repositório "+ i, "Descrição Repositório " + i);
            repositorios.add(repositorio);
        }
        return repositorios;
    }

    private void configuraRecyclerView(List<Repositorio> repositorios) {
        RecyclerView listaNotas = findViewById(R.id.java_pop_recyclerview);
        configuraAdapter(repositorios, listaNotas);
    }

    private void configuraAdapter(List<Repositorio> repositorios, RecyclerView recyclerView) {
        adapter = new ListaJavaPopAdapter(this, repositorios);
        recyclerView.setAdapter(adapter);
    }
}
