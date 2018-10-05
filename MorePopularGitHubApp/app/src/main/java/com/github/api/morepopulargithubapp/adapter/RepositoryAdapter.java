package com.github.api.morepopulargithubapp.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.view.RepositoryItemView;
import com.github.api.morepopulargithubapp.view.RepositoryItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;


@EBean
public class RepositoryAdapter extends RecyclerViewAdapterBase<Repository, RepositoryItemView> {

    @RootContext
    Context context;

    @Override
    protected RepositoryItemView onCreateItemView(ViewGroup parent, int viewType) {
        return RepositoryItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<RepositoryItemView> holder, int position) {
          RepositoryItemView view = holder.getView();
          Repository repository = items.get(position);
          view.bind(repository);
    }

    @Override
    public void setItems(List<Repository> items) {
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
