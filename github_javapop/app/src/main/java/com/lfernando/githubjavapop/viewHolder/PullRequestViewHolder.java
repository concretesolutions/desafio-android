package com.lfernando.githubjavapop.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lfernando.githubjavapop.R;

public class PullRequestViewHolder extends RecyclerView.ViewHolder {
    final public TextView prTitle;
    final public TextView prDescription;
    final public TextView userName;
    final public TextView userFullName;
    final public ImageView avatar;

    public PullRequestViewHolder(@NonNull View view) {
        super(view);
        prTitle = view.findViewById(R.id.prTitleTV);
        prDescription = view.findViewById(R.id.prDescTV);
        userName = view.findViewById(R.id.prUsernameTV);
        userFullName = view.findViewById(R.id.prFullNameTv);
        avatar = view.findViewById(R.id.prAvatarIV);

    }
}
