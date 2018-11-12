package com.jcjm.desafioandroid.recycler.adapters;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcjm.desafioandroid.R;
import com.jcjm.desafioandroid.activities.PullRequestsActivity;
import com.jcjm.desafioandroid.databinding.RepositoryItemBinding;
import com.jcjm.desafioandroid.model.GitRepository;

public class RepositoryPageListAdapter extends PagedListAdapter<GitRepository, RepositoryPageListAdapter.MyViewHolder> {

    public RepositoryPageListAdapter() {
        super(GitRepository.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RepositoryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.repository_item, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.binding.setModel(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RepositoryItemBinding binding;

        MyViewHolder(RepositoryItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

            binding.rlItemRepository.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = itemView.getRoot().getContext();
                    Intent intent = new Intent(context, PullRequestsActivity.class);

                    GitRepository gitRepository = getItem(getLayoutPosition());

                    intent.putExtra("user",gitRepository.getOwner().getLogin());
                    intent.putExtra("repositorio",gitRepository.getName());
                    context.startActivity(intent);
                }
            });



        }
    }
}
