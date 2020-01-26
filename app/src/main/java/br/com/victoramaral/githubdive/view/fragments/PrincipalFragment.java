package br.com.victoramaral.githubdive.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.victoramaral.githubdive.R;
import br.com.victoramaral.githubdive.model.pojos.repositories.Item;
import br.com.victoramaral.githubdive.view.adapter.RepositoryAdapter;
import br.com.victoramaral.githubdive.view.interfaces.RepositoryOnClick;
import br.com.victoramaral.githubdive.viewmodel.PrincipalViewModel;

public class PrincipalFragment extends Fragment implements RepositoryOnClick {

    private PrincipalViewModel principalViewModel;
    private RecyclerView recyclerView;
    private RepositoryAdapter adapter;
    private List<Item> itemList = new ArrayList<>();
    private int page = 1;
    private int perPage = 10;

    public static final String CREATOR_KEY = "123456";
    public static final String REPOSITORY_KEY = "654321";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewModelProviders.of(this).get(PrincipalViewModel.class);
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        principalViewModel = ViewModelProviders.of(this).get(PrincipalViewModel.class);
        recyclerView = view.findViewById(R.id.recyclerViewRepositories);
        adapter = new RepositoryAdapter(itemList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        principalViewModel.getAllRepositories(page, perPage);
        principalViewModel.getListaRepositories().observe(this, items -> {
            if (items != null && !items.isEmpty()) {
                adapter.setUpdate(items);
            } else {
                Toast.makeText(getContext(), "Houve um erro no carregamento dos dados",
                        Toast.LENGTH_SHORT).show();
                adapter.setUpdate(this.itemList);
            }
        });

        scrollListener();

        return view;
    }

    public void scrollListener(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int
                    newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;

                if (totalItemCount > 0 && endHasBeenReached) {
                    page ++;
                    principalViewModel.getAllRepositories(page, perPage);
                }
            }
        });
    }

    @Override
    public void repositoryOnClick(Item item) {
        Bundle bundle = new Bundle();
        bundle.putString(CREATOR_KEY, item.getOwner().getLogin());
        bundle.putString(REPOSITORY_KEY, item.getName());

        Fragment fragment = new RequestFragment();
        fragment.setArguments(bundle);
        replaceFragment(fragment);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.recyclerViewRepositories, fragment);
        transaction.commit();
    }
}
