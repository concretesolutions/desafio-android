package com.lfernando.githubjavapop.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lfernando.githubjavapop.R;

public class RepositoryViewHolder extends RecyclerView.ViewHolder{
    final public TextView repoName;
    final public TextView repoDesc;
    final public TextView starsCount;
    final public TextView forksCount;
    final public TextView userName;
    final public TextView name;
    final public ImageView avatar;

    public RepositoryViewHolder(@NonNull View view) {
        super(view);
        repoName = view.findViewById(R.id.repoNameTV);
        repoDesc = view.findViewById(R.id.repoDescriptionTV);
        starsCount = view.findViewById(R.id.repoStarsTV);
        forksCount = view.findViewById(R.id.repoForksTV);
        userName = view.findViewById(R.id.repoUserNameTv);
        name = view.findViewById(R.id.repoUserFullNameTV);
        avatar = view.findViewById(R.id.repoAvatarIV);
    }




}
