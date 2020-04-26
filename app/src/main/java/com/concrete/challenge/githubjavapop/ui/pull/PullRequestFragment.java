package com.concrete.challenge.githubjavapop.ui.pull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.concrete.challenge.githubjavapop.R;
import com.concrete.challenge.githubjavapop.api.DefaultViewModelFactory;
import com.concrete.challenge.githubjavapop.domain.PullRequest;
import com.concrete.challenge.githubjavapop.ui.BaseFragment;

public class PullRequestFragment extends BaseFragment implements PullRequestRecyclerViewAdapter.ItemClickListener {

    private ProgressBar progressBarCenter;
    private ProgressBar progressBarBottom;
    private LinearLayout emptyState;

    private PullRequestRecyclerViewAdapter adapter;
    private DefaultViewModelFactory viewModelFactory;
    private PullRequestViewModel viewModel;

    private String userName;
    private String repositoryName;
    private boolean isLoading = false;
    private int page = 1;

    public static PullRequestFragment newInstance(Bundle bundle) {
        PullRequestFragment fragment = new PullRequestFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public PullRequestFragment() {
        viewModelFactory = new DefaultViewModelFactory();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pull_request_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        emptyState = getView().findViewById(R.id.empty_state);
        progressBarCenter = getView().findViewById(R.id.progress_bar_center);
        progressBarBottom = getView().findViewById(R.id.progress_bar_bottom);
        emptyState.setVisibility(View.GONE);
        progressBarBottom.setVisibility(View.GONE);

        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view_pull_request);
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
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == viewModel.getPullRequests().getValue().size() - 1) {
                        progressBarBottom.setVisibility(View.VISIBLE);
                        viewModel.loadPullRequests(userName, repositoryName, page);
                        isLoading = true;
                        isIdle = false;
                    }
                }
            }
        });
        adapter = new PullRequestRecyclerViewAdapter(getContext());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PullRequestViewModel.class);

        viewModel.getPullRequests().observe(getViewLifecycleOwner(), pullRequests -> {
            if(pullRequests.size() > 0) {
                adapter.setPullRequests(pullRequests);
                page++;
                progressBarBottom.setVisibility(View.GONE);
                isLoading = false;

                if(emptyState.getVisibility() != View.GONE) {
                    emptyState.setVisibility(View.GONE);
                }
            } else {
                if(emptyState.getVisibility() != View.VISIBLE) {
                    emptyState.setVisibility(View.VISIBLE);
                }
            }
            if(progressBarCenter.getVisibility() != View.GONE) {
                progressBarCenter.setVisibility(View.GONE);
            }
            isIdle = true;
        });

        viewModel.getError().observe(getViewLifecycleOwner(), throwable -> {
            Toast.makeText(getContext(), R.string.error_message_toast, Toast.LENGTH_LONG).show();
        });

        Bundle bundle = getArguments();
        if(bundle != null) {
            userName = bundle.getString(PullRequestActivity.USER_NAME_KEY);
            repositoryName = bundle.getString(PullRequestActivity.REPOSITORY_NAME_KEY);

            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(repositoryName);
            viewModel.loadPullRequests(userName, repositoryName, page);
        }
    }

    @Override
    public void onItemClick(int position) {
        PullRequest pullRequest = viewModel.getPullRequests().getValue().get(position);
        if(pullRequest.htmlUrl != null && !pullRequest.htmlUrl.equals("")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pullRequest.htmlUrl));
            startActivity(intent);
        }
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
