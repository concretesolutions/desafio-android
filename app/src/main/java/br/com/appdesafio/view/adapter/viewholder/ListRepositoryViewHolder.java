package br.com.appdesafio.view.adapter.viewholder;


import android.support.v7.widget.RecyclerView;


import br.com.appdesafio.databinding.RowRepositoryBinding;

public class ListRepositoryViewHolder extends RecyclerView.ViewHolder {
    public RowRepositoryBinding binding;
    public ListRepositoryViewHolder(final RowRepositoryBinding rowRepositoryBinding) {
        super(rowRepositoryBinding.getRoot());
        this.binding = rowRepositoryBinding;
    }

}