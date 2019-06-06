package br.com.desafio.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import br.com.desafio.domain.Repositories;
import br.com.desafio.domain.Repository;
import br.com.desafio.ui.adapter.bind.RepositoryItemView;
import br.com.desafio.ui.adapter.bind.RepositoryItemView_;
import br.com.desafio.ui.adapter.common.RecyclerViewAdapterBase;
import br.com.desafio.ui.adapter.common.ViewWrapper;
import lombok.Setter;

@EBean
public class RepositoryAdapter extends RecyclerViewAdapterBase<Repository, RepositoryItemView> {
    @RootContext
    Context context;
    @Setter
    AdapterView.OnItemClickListener onItemClickListener;

    public void setItems(Repositories repositories){
        super.items = repositories.getItems();
    }

    public void addItem(Repository repository, int position){
        super.items.add(repository);
        notifyItemInserted(position);
    }

    @Override
    protected RepositoryItemView onCreateItemView(ViewGroup parent, int viewType) {
        return RepositoryItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<RepositoryItemView> viewHolder, final int position) {
        RepositoryItemView view = viewHolder.getView();
        Repository repository = items.get(position);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(null, v, position, position);
            }
        });

        view.bind(repository);
    }
}