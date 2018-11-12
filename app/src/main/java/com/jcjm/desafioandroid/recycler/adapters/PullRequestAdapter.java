package com.jcjm.desafioandroid.recycler.adapters;


import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jcjm.desafioandroid.R;
import com.jcjm.desafioandroid.databinding.PullrequestItemBinding;
import com.jcjm.desafioandroid.model.PullRequest;

import java.util.ArrayList;
import java.util.List;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestHolder> {


    private List<PullRequest> data;

    public PullRequestAdapter(){
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public PullRequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        PullrequestItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.pullrequest_item, parent, false);

        return new PullRequestHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestHolder holder, int position) {
        PullRequest pullRequest = data.get(position);
        holder.binding.setPull(pullRequest);

    }

    @Override
    public int getItemCount() {
        return this.data==null?0:this.data.size();
    }

    public void updateData(@Nullable List<PullRequest> data) {
        if (data == null || data.isEmpty()) {
            this.data.clear();
        } else {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public class PullRequestHolder  extends RecyclerView.ViewHolder  {

        PullrequestItemBinding binding;

        public PullRequestHolder(PullrequestItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
