package com.igormeira.githubpop.pullrequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.igormeira.githubpop.R;
import com.igormeira.githubpop.databinding.ItemPullRequestBinding;
import com.igormeira.githubpop.model.PullRequest;
import com.igormeira.githubpop.handler.EventHandler;

import java.util.ArrayList;

public class PullRequestRecyclerAdapter extends RecyclerView.Adapter<PullRequestViewHolder> {
    private ArrayList<PullRequest> mPullRequests;
    private Context context;

    @NonNull @Override
    public PullRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPullRequestBinding itemPullRequestBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_pull_request, parent, false);
        return new PullRequestViewHolder(itemPullRequestBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestViewHolder holder, int position) {
        PullRequest current = mPullRequests.get(position);
        holder.itemPullRequestBinding.setPullrequest(current);
        holder.itemPullRequestBinding.setHandler(new EventHandler(context));
    }

    @Override
    public int getItemCount() {
        return mPullRequests == null ? 0 : mPullRequests.size();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setPullRequestsList(ArrayList<PullRequest> pullRequests) {
        this.mPullRequests = pullRequests;
        notifyDataSetChanged();
    }
}
