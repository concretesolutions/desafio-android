package com.desafioconcret.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.desafioconcret.PullRequestActivity;
import com.desafioconcret.R;
import com.desafioconcret.pojo.json.Repositories;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.ViewHolderRepo> {

    private List<Repositories> repositories = new ArrayList<>();

    public static class ViewHolderRepo extends RecyclerView.ViewHolder {
        public TextView repositoryName;
        public TextView descriptionText;
        public TextView forksNumber;
        public TextView starsNumber;
        public ImageView ownerPhoto;
        public TextView ownerName;


        public ViewHolderRepo(View view) {
            super(view);
            this.repositoryName = view.findViewById(R.id.repo_name);
            this.descriptionText = view.findViewById(R.id.repo_description);
            this.forksNumber = view.findViewById(R.id.repo_forks);
            this.starsNumber = view.findViewById(R.id.repo_stars);
            this.ownerPhoto = view.findViewById(R.id.owner_photo);
            this.ownerName = view.findViewById(R.id.owner_name);

        }
    }

    public void addRepositories(List<Repositories> repositories) {
        this.repositories.addAll(repositories);
    }

    @Override
    public ViewHolderRepo onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_view_item, parent, false);
        return new ViewHolderRepo(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderRepo viewHolder, final int position) {

        viewHolder.repositoryName.setText(this.repositories.get(position).getName());
        viewHolder.descriptionText.setText(this.repositories.get(position).getDescription());
        viewHolder.forksNumber.setText(Integer.toString(this.repositories.get(position).getForks()));
        viewHolder.starsNumber.setText(Integer.toString(this.repositories.get(position).getStargazersCount()));
        Picasso.with(viewHolder.ownerPhoto.getContext()).load(this.repositories.get(position)
                .getOwner().getAvatarUrl()).into(viewHolder.ownerPhoto);
        viewHolder.ownerName.setText(this.repositories.get(position).getOwner().getLogin());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), PullRequestActivity.class);
                String repo_name = repositories.get(position).getName();
                String owner_name = repositories.get(position).getOwner().getLogin();
                intent.putExtra("REPO", repo_name);
                intent.putExtra("OWNER", owner_name);
                v.getContext().startActivity(intent);

            }

        });

    }

    @Override
    public int getItemCount() {
        return this.repositories.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}



