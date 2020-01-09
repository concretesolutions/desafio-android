package com.alexandreobs.testeconcrete.view.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.alexandreobs.testeconcrete.R;
import com.alexandreobs.testeconcrete.model.pojo.GitResult;
import com.alexandreobs.testeconcrete.model.pojo.Item;
import com.alexandreobs.testeconcrete.view.adapter.GitAdapter;
import com.alexandreobs.testeconcrete.view.interfaces.RepositorioOnClick;
import com.alexandreobs.testeconcrete.viewmodel.RepositorioViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RepositorioOnClick {

    private RecyclerView recyclerView;
    private RepositorioViewModel viewModel;
    private GitAdapter adapter;
    private List<Item> itemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerRepos);
        viewModel = ViewModelProviders.of(this).get(RepositorioViewModel.class);
        adapter = new GitAdapter(itemList, this);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        viewModel.getRepositorios();
        viewModel.getListaRespositorios().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> results1) {
                adapter.update(results1);
            }
        });


    }

    @Override
    public void OnClick(GitResult result) {

    }
}
