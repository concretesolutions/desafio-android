package br.com.victoramaral.githubdive.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.victoramaral.githubdive.R;
import br.com.victoramaral.githubdive.model.pojos.repositories.Item;
import br.com.victoramaral.githubdive.view.adapter.RepositoryAdapter;
import br.com.victoramaral.githubdive.viewmodel.PrincipalViewModel;

public class PrincipalFragment extends Fragment {

    private PrincipalViewModel principalViewModel;
    private RecyclerView recyclerView;
    private RepositoryAdapter adapter;
    private List<Item> itemList = new ArrayList<>();
    private int page = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewModelProviders.of(this).get(PrincipalViewModel.class);
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewRepositories);
        adapter = new RepositoryAdapter(itemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        principalViewModel = ViewModelProviders.of(this).get(PrincipalViewModel.class);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        principalViewModel.getAllRepositories(page);
        principalViewModel.getListaRepositories().observe(this, items -> {
            if (items != null && !items.isEmpty()) {
                adapter.setUpdate(items);
            } else {
                Toast.makeText(getContext(), "Houve um erro no carregamento dos dados",
                        Toast.LENGTH_SHORT).show();
                adapter.setUpdate(this.itemList);
            }
        });

        return view;
    }
}