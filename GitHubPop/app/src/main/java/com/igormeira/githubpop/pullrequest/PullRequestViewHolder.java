package com.igormeira.githubpop.pullrequest;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.igormeira.githubpop.databinding.ItemPullRequestBinding;

public class PullRequestViewHolder extends RecyclerView.ViewHolder {

    ItemPullRequestBinding itemPullRequestBinding;

    public PullRequestViewHolder(@NonNull ItemPullRequestBinding itemPullRequestBinding) {
        super(itemPullRequestBinding.getRoot());
        this.itemPullRequestBinding = itemPullRequestBinding;
    }

}
