package com.igormeira.githubpop.repository;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.igormeira.githubpop.databinding.ItemRepositoryBinding;

public class RepositoryViewHolder extends RecyclerView.ViewHolder {

    ItemRepositoryBinding itemRepositoryBinding;

    public RepositoryViewHolder(@NonNull ItemRepositoryBinding itemRepositoryBinding) {
        super(itemRepositoryBinding.getRoot());
        this.itemRepositoryBinding = itemRepositoryBinding;
    }
}
