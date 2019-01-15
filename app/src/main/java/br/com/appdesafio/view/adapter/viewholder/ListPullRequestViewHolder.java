package br.com.appdesafio.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;

import br.com.appdesafio.databinding.RowPullRequestBinding;


/**
 * class view holder of the adapter that lists the pull requeste of a repository.
 */
public class ListPullRequestViewHolder extends RecyclerView.ViewHolder {
    public RowPullRequestBinding binding;

    public ListPullRequestViewHolder(final RowPullRequestBinding rowRepositoryBinding) {
        super(rowRepositoryBinding.getRoot());
        this.binding = rowRepositoryBinding;
    }

}