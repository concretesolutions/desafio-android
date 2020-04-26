package com.concrete.challenge.githubjavapop.ui.repository;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.concrete.challenge.githubjavapop.R;
import com.concrete.challenge.githubjavapop.api.DefaultViewModelFactory;
import com.concrete.challenge.githubjavapop.ui.pull.PullRequestActivity;

public class RepositoryFragment extends Fragment implements  RepositoryRecyclerViewAdapter.ItemClickListener, IdlingResource {

    private RepositoryViewModel viewModel;
    private RepositoryRecyclerViewAdapter adapter;
    private DefaultViewModelFactory viewModelFactory;

    private ProgressBar progressBarCenter;
    private ProgressBar progressBarBottom;

    private boolean isLoading = false;
    private int page;
    private boolean isReady;
    private ResourceCallback resourceCallback;

    public static RepositoryFragment newInstance() {
        return new RepositoryFragment();
    }

    public RepositoryFragment() {
        viewModelFactory = new DefaultViewModelFactory();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.repository_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        page = 1;
        progressBarCenter = getView().findViewById(R.id.progress_bar_center);
        progressBarBottom = getView().findViewById(R.id.progress_bar_bottom);

        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view_repository);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == viewModel.getRepositories().getValue().size() - 1) {
                        progressBarBottom.setVisibility(View.VISIBLE);
                        viewModel.loadRepositories(page);
                        isLoading = true;

                    }
                }
            }
        });
        adapter = new RepositoryRecyclerViewAdapter(getContext());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepositoryViewModel.class);

        viewModel.getRepositories().observe(getViewLifecycleOwner(), repositories -> {
            if(repositories.size() > 0) {
                adapter.setRepositories(repositories);
                page++;
                progressBarBottom.setVisibility(View.GONE);
                isLoading = false;

                if(progressBarCenter.getVisibility() != View.GONE) {
                    progressBarCenter.setVisibility(View.GONE);
                }
            }
            isReady = true;
        });

        viewModel.getError().observe(getViewLifecycleOwner(), integer -> {
            Toast.makeText(getContext(), R.string.error_message_toast, Toast.LENGTH_LONG).show();
        });

        viewModel.loadRepositories(page);
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getActivity(), PullRequestActivity.class));
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        if(isReady) resourceCallback.onTransitionToIdle();
        return isReady;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }
}
