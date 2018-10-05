package com.github.api.morepopulargithubapp.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.github.api.morepopulargithubapp.model.vo.PullRequest;
import com.github.api.morepopulargithubapp.view.PullRequestItemView;
import com.github.api.morepopulargithubapp.view.PullRequestItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;


@EBean
public class PullRequestAdapter extends RecyclerViewAdapterBase<PullRequest, PullRequestItemView> {

    @RootContext
    Context context;

    @Override
    protected PullRequestItemView onCreateItemView(ViewGroup parent, int viewType) {
        return PullRequestItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<PullRequestItemView> holder, int position) {
        PullRequestItemView view = holder.getView();
          PullRequest pullRequest = items.get(position);
          view.bind(pullRequest);
    }

    @Override
    public void setItems(List<PullRequest> items) {
        super.setItems(items);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
