package com.br.apigithub.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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
import com.br.apigithub.beans.Pull;
import com.br.apigithub.utils.NetworkUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rlima on 04/10/18.
 */

public class PullRequestFragment extends Fragment implements PullRequestAdapter.ItemClickListener {
    public static final String TAG = PullRequestFragment.class.getSimpleName().toUpperCase();

    public static final int PAGE_SIZE = 10;
    private RepositoryViewModel repoViewModel;
    private String msgError;
    @BindView(R.id.rv_pull_request)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_wifi)
    LinearLayout layoutNoWifi;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.layout_no_pull_request)
    ConstraintLayout layoutNoPullRequest;
    private List<Pull> pulls;
    private LinearLayoutManager layoutManager;
    private boolean isLastPage = false;
    private int page = 1;
    private PullRequestAdapter adapter;

    Observer<String> observerMsgError = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            msgError = s;
            ((MainActivity) getActivity()).getProgressDialog().dismiss();
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        }
    };

    Observer<List<Pull>> observerPullRequests = new Observer<List<Pull>>() {
        @Override
        public void onChanged(@Nullable List<Pull> pulls) {
            if (pulls == null || pulls.isEmpty()) {
                layoutNoPullRequest.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                layoutNoPullRequest.setVisibility(View.INVISIBLE);
                adapter.setPullRequests(pulls);
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    };

    public static PullRequestFragment newInstance() {
        PullRequestFragment pullRequestFragment = new PullRequestFragment();

        return pullRequestFragment;
    }

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
                    repoViewModel.updatePulls(++page);
                    progressBar.setVisibility(View.VISIBLE);
                    isLastPage = true;
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pull, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configViewModel();
        configRecyclerView();
        verifyInternetConnection();
    }

    private void configRecyclerView() {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        if (adapter == null) {
            adapter = new PullRequestAdapter(getActivity(), this);
        }
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), View.SCROLL_AXIS_HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
    }

    private void configViewModel() {
        repoViewModel = ViewModelProviders.of(getActivity()).get(RepositoryViewModel.class);
        repoViewModel.getMsgError().observe(getActivity(), observerMsgError);
        repoViewModel.getPullsLiveData().observe(getActivity(), observerPullRequests);
    }

    public void verifyInternetConnection() {
        if (NetworkUtils.isConnected(getActivity())) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            showLayoutNoConnectivity(View.VISIBLE);
        }
    }

    private void showLayoutNoConnectivity(int visible) {
        layoutNoWifi.setVisibility(visible);
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
