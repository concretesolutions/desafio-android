package br.com.appdesafio.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;

import br.com.appdesafio.databinding.RowPullRequestBinding;


public class ListPullRequestViewHolder extends RecyclerView.ViewHolder {
    public RowPullRequestBinding binding;
    public ListPullRequestViewHolder(final RowPullRequestBinding rowRepositoryBinding) {
        super(rowRepositoryBinding.getRoot());
        this.binding = rowRepositoryBinding;
    }

}