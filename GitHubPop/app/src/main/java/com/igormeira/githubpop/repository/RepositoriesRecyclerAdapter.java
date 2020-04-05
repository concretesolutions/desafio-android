package com.igormeira.githubpop.repository;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.igormeira.githubpop.R;
import com.igormeira.githubpop.databinding.ItemRepositoryBinding;
import com.igormeira.githubpop.handler.EventHandler;
import com.igormeira.githubpop.model.Repository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RepositoriesRecyclerAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {
    private ArrayList<Repository> mRepositories = new ArrayList<>();
    private Context context;

    @NonNull @Override
    public RepositoryViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemRepositoryBinding itemRepositoryBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_repository, parent, false);
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
        if (repositories != null) {
            this.mRepositories.addAll(repositories);
            notifyDataSetChanged();
        }
    }
}
