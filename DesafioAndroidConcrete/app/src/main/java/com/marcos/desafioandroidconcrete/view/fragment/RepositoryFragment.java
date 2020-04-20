package com.marcos.desafioandroidconcrete.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.marcos.desafioandroidconcrete.R;
import com.marcos.desafioandroidconcrete.adapter.RepositoryAdapter;
import com.marcos.desafioandroidconcrete.domain.Repository;
import com.marcos.desafioandroidconcrete.domain.RepositoryDetail;
import com.marcos.desafioandroidconcrete.util.ItemClickListener;
import com.marcos.desafioandroidconcrete.util.Methods;
import com.marcos.desafioandroidconcrete.view.MainActivity;
import com.marcos.desafioandroidconcrete.viewmodel.RepositoryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepositoryFragment extends Fragment {
    public static final String PAGE = "PAGE";
    public static final String ITEMS = "ITEMS";
    public static final String REPOSITORY = "REPOSITORY";

    Repository repository;
    private RepositoryAdapter adapter;
    RecyclerView recyclerView;
    View contextView;
    private Activity activity;
    LinearLayoutManager mLayoutManager;
    int page = 1;
    private boolean isLoadingData = false;
    private List<RepositoryDetail> listItems = new ArrayList<>();
    View v;
    RepositoryViewModel repositoryViewModel;

    public RepositoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_repository, container, false);
        initialize();
        configViewModel();
        validateSavedInstance(savedInstanceState);

        return v;
    }

    private void configViewModel() {
        repositoryViewModel = ViewModelProviders.of(this).get(RepositoryViewModel.class);
        repositoryViewModel.repositoryMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Repository>() {
            @Override
            public void onChanged(Repository newRepository) {
                if (newRepository != null) {
                    saveData(newRepository);
                    Methods.showProgress(((MainActivity) activity), false);
                } else {
                    Snackbar.make(contextView, R.string.error, Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });

    }

    private void initialize() {
        activity = getActivity();
        activity.setTitle(R.string.repositories);
        contextView = v.findViewById(R.id.context_view);
        recyclerView = v.findViewById(R.id.recyclerview);

    }

    private void validateSavedInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            repository = (Repository) savedInstanceState.getSerializable(REPOSITORY);
            listItems = savedInstanceState.getParcelableArrayList(ITEMS);
            page = savedInstanceState.getInt(PAGE);
            configRecycleview();
        } else if (repository != null) {
            configRecycleview();
        } else {
            Methods.showProgress(((MainActivity) activity), true);
            repositoryViewModel.getRepositories(Methods.fetchToken(activity), page);
        }
    }


    public void saveData(Repository repository) {
        if (repository != null && this.repository == null) {
            this.repository = repository;
            listItems = this.repository.getItems();
            configRecycleview();
        } else {
            listItems = Stream.concat(listItems.stream(), repository.getItems().stream())
                    .collect(Collectors.toList());
            adapter.update(listItems);
            this.repository.setItems(listItems);
        }
        isLoadingData = false;
    }


    private void configRecycleview() {
        adapter = new RepositoryAdapter(this.listItems);

        mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setHasFixedSize(true); //boa pratica
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                RepositoryFragmentDirections.NextAction nextAction = RepositoryFragmentDirections.nextAction(repository.getItems().get(position).getName(), repository.getItems().get(position).getOwner().getLogin());
                Navigation.findNavController(v).navigate(nextAction);

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //Last visible item position.
                int lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition();

                if (!isLoadingData && listItems.size() == (lastVisiblePosition + 1)) {
                    page++;
                    isLoadingData = true;
                    Methods.showProgress(((MainActivity) activity), true);
                    repositoryViewModel.getRepositories(Methods.fetchToken(activity), page);
                }
            }
        });

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(REPOSITORY, repository);
        outState.putParcelableArrayList(ITEMS, (ArrayList<? extends Parcelable>) listItems);
        outState.putInt(PAGE, page);
        super.onSaveInstanceState(outState);
    }
}
