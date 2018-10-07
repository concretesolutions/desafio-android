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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.br.apigithub.R;
import com.br.apigithub.aac.RepositoryViewModel;
import com.br.apigithub.beans.GithubRepository;
import com.br.apigithub.utils.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryFragment extends Fragment {
    public static final String TAG = RepositoryFragment.class.getSimpleName().toUpperCase();

    private RepositoryViewModel repoViewModel;
    private String msgError;
    public static int PAGE_SIZE = 10;

    @BindView(R.id.rv_repository)
    RecyclerView mRecyclerView;

    @BindView(R.id.ll_no_wifi)
    LinearLayout layoutNoWifi;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private RepositoryAdapter adapter;
    private LinearLayoutManager layoutManager;
    private boolean isLastPage = false;
    private int page = 1;

    Observer<String> observerMsgError = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            msgError = s;
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        }
    };

    Observer<GithubRepository> observerRepos = new Observer<GithubRepository>() {
        @Override
        public void onChanged(@Nullable GithubRepository githubRepositories) {
            adapter.setRepository(githubRepositories);
            progressBar.setVisibility(View.INVISIBLE);
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

            if (!isLastPage && dy > 0) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                    repoViewModel.listRepos(++page);
                    progressBar.setVisibility(View.VISIBLE);
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
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        configViewModel();
        configRecyclerView();
        verifyInternetConnection();
    }

    private void configViewModel() {
        repoViewModel = ViewModelProviders.of(getActivity()).get(RepositoryViewModel.class);
        repoViewModel.getMsgError().observe(getActivity(), observerMsgError);
        repoViewModel.getGithubLiveData().observe(getActivity(), observerRepos);
    }

    private void configRecyclerView() {
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        if (adapter == null) {
            adapter = new RepositoryAdapter((MainActivity) getActivity());
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), View.SCROLL_AXIS_HORIZONTAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
    }

    public void verifyInternetConnection() {
        if (NetworkUtils.isConnected(getActivity())) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            showLayoutNoConnectivity(View.VISIBLE);
        }
    }

    private void showLayoutNoConnectivity(int visible) {
        layoutNoWifi.setVisibility(visible);
        mRecyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

}
