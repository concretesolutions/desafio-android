package com.igormeira.githubpop.repository;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.igormeira.githubpop.R;
import com.igormeira.githubpop.databinding.ItemRepositoryBinding;
import com.igormeira.githubpop.model.Repository;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;
import java.util.List;

public class RepositoriesRecyclerAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {
    private ArrayList<Repository> mRepositories;
    private Context context;

    @NonNull @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        ItemRepositoryBinding itemRepositoryBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_repository, viewGroup, false);
        return new RepositoryViewHolder(itemRepositoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        Repository current = mRepositories.get(position);
        holder.itemRepositoryBinding.setRepository(current);
        holder.itemRepositoryBinding.setHandler(new EventHandler(context));
    }

    @Override
    public int getItemCount() {
        return mRepositories == null ? 0 : mRepositories.size();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setRepositoryList(ArrayList<Repository> repositories) {
        this.mRepositories = repositories;
        notifyDataSetChanged();
    }
}
