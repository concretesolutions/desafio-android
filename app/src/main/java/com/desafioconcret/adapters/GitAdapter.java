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
import com.desafioconcret.pojo.json.Repo;
import com.desafioconcret.pojo.json.Repositories;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.ViewHolder>{

    private List<Repositories> repositories;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView repositoryName;
        public TextView descriptionText;
        public TextView forksNumber;
        public TextView starsNumber;
        public ImageView ownerPhoto;
        public TextView ownerName;


        public ViewHolder(View view) {
            super(view);
            this.repositoryName = (TextView) view.findViewById(R.id.repo_name);
            this.descriptionText = (TextView) view.findViewById(R.id.repo_description);
            this.forksNumber = (TextView) view.findViewById(R.id.repo_forks);
            this.starsNumber = (TextView) view.findViewById(R.id.repo_stars);
            this.ownerPhoto = (ImageView) view.findViewById(R.id.owner_photo);
            this.ownerName = (TextView) view.findViewById(R.id.owner_name);

        }
    }


    public GitAdapter(List<Repositories> repositorios) {
        if (repositorios!= null) {
            return;
        }else{
            this.repositories.addAll(repositorios);
        }

    }

    @Override
    public GitAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.repositoryName = (TextView) view.findViewById(R.id.repo_name);
        viewHolder.descriptionText = (TextView) view.findViewById(R.id.repo_description);
        viewHolder.forksNumber = (TextView) view.findViewById(R.id.repo_forks);
        viewHolder.starsNumber = (TextView) view.findViewById(R.id.repo_stars);
        viewHolder.ownerPhoto = (ImageView) view.findViewById(R.id.owner_photo);
        viewHolder.ownerName = (TextView) view.findViewById(R.id.owner_name);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

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
    public int getItemCount() { return this.repositories.size(); }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

//    public adapterUpdate (List<Repositories> repositories){
//        this.repositories = repositories;
//        this.repositories.addAll(repositories);
//        notifyDataSetChanged();
//    }
}



