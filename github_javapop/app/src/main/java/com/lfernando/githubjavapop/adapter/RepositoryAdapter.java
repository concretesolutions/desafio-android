package com.lfernando.githubjavapop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lfernando.githubjavapop.R;
import com.lfernando.githubjavapop.model.Repo;
import com.lfernando.githubjavapop.viewHolder.RepositoryViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter {
    private List<Repo> repoList;
    private Context context;

    public RepositoryAdapter(List<Repo> repoList, Context context) {
        this.repoList = repoList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.repository_item, viewGroup, false);
        RepositoryViewHolder holder = new RepositoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        RepositoryViewHolder holder = (RepositoryViewHolder) viewHolder;
        Repo repo = repoList.get(i);
        holder.repoName.setText(repo.getName());
        holder.repoDesc.setText(repo.getDescription());
        holder.forksCount.setText(String.valueOf(repo.getForks()));
        holder.starsCount.setText(String.valueOf(repo.getStars()));
        holder.name.setText(repo.getOwner().getLogin());
        holder.userName.setText(repo.getOwner().getName());
        Picasso.get().load(repo.getOwner().getAvatarUrl()).into(holder.avatar);

    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }
}
