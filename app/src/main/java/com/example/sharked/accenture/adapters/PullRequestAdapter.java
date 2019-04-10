package com.example.sharked.accenture.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sharked.accenture.R;
import com.example.sharked.accenture.holders.PullRequestHolder;
import com.example.sharked.accenture.holders.RepositoryHolder;
import com.example.sharked.accenture.models.PullRequest;
import com.example.sharked.accenture.models.Repository;
import com.example.sharked.accenture.views.activities.PullRequestListActivity;
import com.example.sharked.accenture.views.activities.PullRequestListActivity_;

import java.util.ArrayList;


public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestHolder> {
    private Context ctx;
    private ArrayList<PullRequest> items;

    public PullRequestAdapter(@NonNull Context context, @NonNull ArrayList<PullRequest> objects) {
        ctx = context;
        items = objects;

    }


    @Override
    public PullRequestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(ctx).inflate(R.layout.li_pull_request, parent, false);
        return new PullRequestHolder(view);
    }

    @Override
    public void onBindViewHolder(PullRequestHolder holder, int position) {
        PullRequest item = items.get(position);
        holder.prTitleTV.setText(item.getTitle());
        holder.prBodyTV.setText(item.getBody());
        holder.prOwnerTypeTV.setText(item.getUser().getLogin());
        holder.prOwnerTV.setText(item.getUser().getType());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
