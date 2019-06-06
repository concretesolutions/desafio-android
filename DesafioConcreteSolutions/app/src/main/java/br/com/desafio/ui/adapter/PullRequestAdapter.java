package br.com.desafio.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import br.com.desafio.domain.PullRequest;
import br.com.desafio.ui.adapter.bind.PullRequestItemView;
import br.com.desafio.ui.adapter.bind.PullRequestItemView_;
import br.com.desafio.ui.adapter.common.RecyclerViewAdapterBase;
import br.com.desafio.ui.adapter.common.ViewWrapper;
import lombok.Setter;

@EBean
public class PullRequestAdapter extends RecyclerViewAdapterBase<PullRequest, PullRequestItemView> {
    @RootContext
    Context context;
    @Setter
    AdapterView.OnItemClickListener onItemClickListener;

    public void setItems(List<PullRequest> pullRequests){
        super.items = pullRequests;
    }

    public void addItem(PullRequest pullRequest, int position){
        super.items.add(pullRequest);
        notifyItemInserted(position);
    }

    @Override
    protected PullRequestItemView onCreateItemView(ViewGroup parent, int viewType) {
        return PullRequestItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<PullRequestItemView> viewHolder, final int position) {
        PullRequestItemView view = viewHolder.getView();
        PullRequest pullRequest = items.get(position);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(null, v, position, position);
            }
        });

        view.bind(pullRequest);
    }
}