package com.br.apigithub.view;

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
import android.widget.TextView;

import com.br.apigithub.R;
import com.br.apigithub.beans.Pull;

import java.util.List;

/**
 * Created by rlima on 04/10/18.
 */

public class PullRequestFragment extends Fragment {
    public static final int PAGE_SIZE = 10;
    private RecyclerView recyclerView;
    private TextView inforSearch;
    private LinearLayout layoutNoPullRequests;
    private List<Pull> pulls;
    private LinearLayoutManager layoutManager;
    private boolean isLastPage = false;
    private int page = 1;

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
                    ((MainActivity) getActivity()).getRepoViewModel().updatePulls(++page, PAGE_SIZE);
                    ((MainActivity) getActivity()).getProgressDialog().show();
                    isLastPage = true;
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pull, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inforSearch = view.findViewById(R.id.info_serarch_pull);
        recyclerView = view.findViewById(R.id.recycler_pr);
        layoutNoPullRequests = view.findViewById(R.id.layout_no_pr);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), View.SCROLL_AXIS_HORIZONTAL));
    }

    public void setAdapter() {
        if (pulls == null || pulls.size() == 0) {
            layoutNoPullRequests.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setAdapter(new PullRequestAdapter(pulls, getActivity()));
            recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setFocusable(true);
            recyclerView.requestFocus();
        }
        inforSearch.setVisibility(View.GONE);
        ((MainActivity) getActivity()).getProgressDialog().dismiss();
    }

    public void updateAdapter() {
        ((MainActivity) getActivity()).getProgressDialog().dismiss();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public List<Pull> getPulls() {
        return pulls;
    }

    public void setPulls(List<Pull> pulls) {
        this.pulls = pulls;
        isLastPage = false;
    }
}
