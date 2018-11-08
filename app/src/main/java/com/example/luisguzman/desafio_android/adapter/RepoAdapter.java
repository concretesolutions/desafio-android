package com.example.luisguzman.desafio_android.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.luisguzman.desafio_android.PullReqActivity;
import com.example.luisguzman.desafio_android.R;
import com.example.luisguzman.desafio_android.modal.RepoList;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ReposViewHolder> {

    private List<RepoList> reposList;
    private Context context;

    public RepoAdapter(List<RepoList> reposList, Context context) {
        this.reposList = reposList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReposViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new ReposViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReposViewHolder ReposViewHolder, int i) {
        final RepoList repoList = reposList.get(i);

        ReposViewHolder.repositoryName.setText(repoList.getName());
        ReposViewHolder.repositoryDescription.setText(repoList.getDescription());
        Glide.with(context)
                .load(repoList.getOwner().getAvatar())
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(ReposViewHolder.authorImage);
        ReposViewHolder.authorName.setText(repoList.getOwner().getLogin());
        ReposViewHolder.forksCount.setText(repoList.getForks());
        ReposViewHolder.starsCount.setText(repoList.getStars());

        ReposViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PullReqActivity.class);
                intent.putExtra("Owner", repoList.getOwner().getLogin());
                intent.putExtra("Name", repoList.getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

    public class ReposViewHolder extends RecyclerView.ViewHolder {

        public TextView repositoryName;
        public TextView repositoryDescription;
        public CircleImageView authorImage;
        public TextView authorName;
        public TextView forksCount;
        public TextView starsCount;
        public ImageView forksIcon;
        public ImageView starsIcon;
        public LinearLayout parentLayout;

        public ReposViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.item_layout);
            repositoryName = itemView.findViewById(R.id.name);
            repositoryDescription = itemView.findViewById(R.id.description);
            authorImage = itemView.findViewById(R.id.author_image);
            authorName = itemView.findViewById(R.id.author_name);
            forksCount = itemView.findViewById(R.id.fork_number);
            starsCount = itemView.findViewById(R.id.stars_state);
            forksIcon = itemView.findViewById(R.id.fork_icon);
            starsIcon = itemView.findViewById(R.id.stars_icon);
        }
    }
}