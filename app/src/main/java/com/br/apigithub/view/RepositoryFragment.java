package com.br.apigithub.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.br.apigithub.R;
import com.br.apigithub.aac.RepositoryViewModel;
import com.br.apigithub.beans.GithubRepository;
import com.br.apigithub.utils.NetworkUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryFragment extends Fragment {
    private RepositoryViewModel repoViewModel;
    private String msgError;
    public static int PAGE_SIZE = 10;

    @BindView(R.id.rv_repository)
    RecyclerView mRecyclerView;

    private RepositoryAdapter adapter;
    private LinearLayoutManager layoutManager;
    private boolean isLastPage = false;
    private int page = 1;

    Observer<String> observerMsgError = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            msgError = s;
            ((MainActivity) getActivity()).getProgressDialog().dismiss();
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        }
    };

    Observer<List<GithubRepository>> observerRepos = new Observer<List<GithubRepository>>() {
        @Override
        public void onChanged(@Nullable List<GithubRepository> githubRepositories) {
            adapter.setRepositories(githubRepositories);
        }
    };

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!((MainActivity) getActivity()).getProgressDialog().isShowing() && !isLastPage && dy > 0) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                    repoViewModel.updateListRepos(++page, PAGE_SIZE);
                    ((MainActivity) getActivity()).getProgressDialog().show();
                    isLastPage = true;
                }
            }
        }
    };

    public static RepositoryFragment newInstance() {
        RepositoryFragment fragment = new RepositoryFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        repoViewModel = ViewModelProviders.of(getActivity()).get(RepositoryViewModel.class);
        repoViewModel.getMsgError().observe(this, observerMsgError);
        repoViewModel.getGithubLiveData().observe(this, observerRepos);
        repoViewModel.init();

        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new RepositoryAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), View.SCROLL_AXIS_HORIZONTAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        verifyInternetConnection();
    }

    public void verifyInternetConnection() {
        if (NetworkUtils.isConnected(getActivity())) {
            repoViewModel.listRepos(page);
        } else {
//            showLayoutNoConnectivity(View.VISIBLE);
        }
    }
}
