package br.com.guilherme.concrete.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.guilherme.concrete.R;
import br.com.guilherme.concrete.adapter.RepositorioAdapter;
import br.com.guilherme.concrete.model.Repositorio;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listRepo = findViewById(R.id.list_rpositorios);

        setRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setRecyclerView(){
        ArrayList<Repositorio> repositorios = new ArrayList<>();

        for (int i = 0; i <= 10; i ++){
            Repositorio repo = new Repositorio();
            repo.setNomeRepositorio("Teste " + i);
            repositorios.add(repo);
        }

        RepositorioAdapter adapter = new RepositorioAdapter(repositorios, MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        listRepo.setLayoutManager(linearLayoutManager);
        listRepo.setAdapter(adapter);
        listRepo.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

    }
}
