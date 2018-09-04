package com.example.rafaelanastacioalves.moby.repolisting;

import android.arch.paging.PagedListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.vo.Repo;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;


public class RepoListAdapter extends PagedListAdapter<Repo, RepoViewHolder> {
    private RecyclerViewClickListener recyclerViewClickListener;


    protected RepoListAdapter() {
        super(DIFF_CALLBACK);
    }


    public void setRecyclerViewClickListener(RecyclerViewClickListener aRVC) {
        this.recyclerViewClickListener = aRVC;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_viewholder, parent, false), recyclerViewClickListener);
    }


    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        Repo aRepoW = getItem(position);
        ((RepoViewHolder) holder).bind(aRepoW);
    }

    private static final DiffUtil.ItemCallback<Repo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Repo>() {
        @Override
        public boolean areItemsTheSame(Repo oldItem, Repo newItem) {

            // a simple comparision suffices here
            return oldItem.getName() == newItem.getName() && oldItem.getOwner() == newItem.getOwner();
        }

        @Override
        public boolean areContentsTheSame(Repo oldItem, Repo newItem) {
            return oldItem == newItem;
        }
    };

}

