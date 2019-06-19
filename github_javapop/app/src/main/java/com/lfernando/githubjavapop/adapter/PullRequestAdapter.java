package com.lfernando.githubjavapop.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lfernando.githubjavapop.R;
import com.lfernando.githubjavapop.model.PullRequest;
import com.lfernando.githubjavapop.viewHolder.PullRequestViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class PullRequestAdapter extends RecyclerView.Adapter {
    private List<PullRequest> pullRequestList;
    private Context context;

    public PullRequestAdapter(List<PullRequest> pullRequestList, Context context) {
        this.pullRequestList = pullRequestList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.pr_item, viewGroup, false);
        PullRequestViewHolder holder = new PullRequestViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PullRequestViewHolder holder = (PullRequestViewHolder) viewHolder;
        final PullRequest pr = pullRequestList.get(i);
        holder.prTitle.setText(pr.getTitle());
        holder.prDescription.setText(pr.getBody());
        holder.userFullName.setText(pr.getUser().getName());
        holder.userName.setText(pr.getUser().getLogin());
        Picasso.get().load(pr.getUser().getAvatarUrl()).transform(new CropCircleTransformation()).into(holder.avatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(pr.getUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pullRequestList.size();
    }
}
