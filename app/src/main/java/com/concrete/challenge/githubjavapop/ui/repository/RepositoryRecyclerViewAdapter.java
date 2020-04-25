package com.concrete.challenge.githubjavapop.ui.repository;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.concrete.challenge.githubjavapop.R;
import com.concrete.challenge.githubjavapop.domain.Repository;
import com.concrete.challenge.githubjavapop.domain.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RepositoryRecyclerViewAdapter extends RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Repository> data;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;
    private Context context;

    public RepositoryRecyclerViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.repository_card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repository repository = data.get(position);
        User user = repository.owner;
        if(user != null) Picasso.get().load(user.avatarUrl).into(holder.userImage);
        holder.repositoryName.setText(repository.name);
        holder.repositoryDesc.setText(repository.description);
        holder.forkCount.setText(repository.forksCount != null ? repository.forksCount.toString() : "");
        holder.starCount.setText(repository.stargazersCount != null ? repository.stargazersCount.toString() : "");
        holder.userName.setText(user.login);
        holder.userFullName.setText(user.name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setRepositories(ArrayList<Repository> repository) {
        data = repository;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView repositoryName;
        public TextView repositoryDesc;
        public TextView forkCount;
        public TextView starCount;
        public TextView userName;
        public TextView userFullName;
        public ImageView userImage;

        ViewHolder(View itemView) {
            super(itemView);
            repositoryName = itemView.findViewById(R.id.repository_name);
            repositoryDesc = itemView.findViewById(R.id.repository_desc);
            forkCount = itemView.findViewById(R.id.fork_count);
            starCount = itemView.findViewById(R.id.star_count);
            userName = itemView.findViewById(R.id.username);
            userFullName = itemView.findViewById(R.id.user_full_name);
            userImage = itemView.findViewById(R.id.user_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(getAdapterPosition());
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
