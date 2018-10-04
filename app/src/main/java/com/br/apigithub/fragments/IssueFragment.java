package com.br.apigithub.fragments;

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

import com.br.apigithub.view.MainActivity;
import com.br.apigithub.R;
import com.br.apigithub.beans.Issue;
import com.br.apigithub.view.IssueAdapter;

import java.util.List;

/**
 * Created by rlima on 04/10/18.
 */

public class IssueFragment extends Fragment {
    public static final int PAGE_SIZE = 10;
    private RecyclerView recyclerView;
    private TextView inforSearch;
    private LinearLayout layoutNoIssue;
    private List<Issue> issues;
    private LinearLayoutManager layoutManager;
    private boolean isLastPage = false;
    private int page = 1;

    public static IssueFragment newInstance() {
        IssueFragment issueFragment = new IssueFragment();
        return issueFragment;
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
                    ((MainActivity) getActivity()).getRepoViewModel().updateIssues(++page, PAGE_SIZE);
                    ((MainActivity) getActivity()).getProgressDialog().show();
                    isLastPage = true;
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_issue, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inforSearch = view.findViewById(R.id.text_info_search);
        recyclerView = view.findViewById(R.id.recycler_issue);
        layoutNoIssue = view.findViewById(R.id.layout_no_issue);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), View.SCROLL_AXIS_HORIZONTAL));
    }

    public void setAdapter() {
        if (issues == null || issues.size() == 0) {
            layoutNoIssue.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setAdapter(new IssueAdapter(issues, getActivity()));
            recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setFocusable(true);
            recyclerView.requestFocus();
        }
        inforSearch.setVisibility(View.GONE);
        ((MainActivity) getActivity()).stopLoading();
    }

    public void updateAdapter() {
        ((MainActivity) getActivity()).stopLoading();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void hideRecylerView() {
        inforSearch.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
        isLastPage = false;
    }
}
