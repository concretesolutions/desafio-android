package com.paulobsa.desafioandroid;

import android.content.Context;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paulobsa.desafioandroid.model.Item;
import com.paulobsa.desafioandroid.model.SearchResult;

import java.util.ArrayList;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoListAdapterHolder> {
    private RepoListAdapterOnclickHandler mHandler;
    private Context mContext;
    private SearchResult searchResult;

    public RepoListAdapter(RepoListAdapterOnclickHandler handler, Context context) {
        this.mHandler = handler;
        this.mContext = context;
        this.searchResult = new SearchResult();
        this.searchResult.setItems(new ArrayList<Item>());
    }

    @Override
    public RepoListAdapter.RepoListAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.repo_info_card, parent, false);

        return new RepoListAdapter.RepoListAdapterHolder(view);

    }

    @Override
    public void onBindViewHolder(RepoListAdapter.RepoListAdapterHolder repoListAdapterHolder, final int position) {
        repoListAdapterHolder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.onCardClick(searchResult.getItems().get(position).getName());
            }
        });

        repoListAdapterHolder.textView.setText(searchResult.getItems().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return searchResult.getItems().size();
    }

    public class RepoListAdapterHolder extends RecyclerView.ViewHolder {
        CardView mCard;
        TextView textView;
        public RepoListAdapterHolder(View itemView) {
            super(itemView);

            this.mCard = itemView.findViewById(R.id.repo_info_card);
            this.textView = itemView.findViewById(R.id.repo_name_txt);
        }
    }

    public interface RepoListAdapterOnclickHandler {
        void onCardClick(String repoJson);
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }
}
