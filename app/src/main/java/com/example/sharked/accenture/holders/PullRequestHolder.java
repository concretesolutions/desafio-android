package com.example.sharked.accenture.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sharked.accenture.R;

public class PullRequestHolder extends RecyclerView.ViewHolder {
    public TextView prTitleTV;
    public TextView prBodyTV;
    public TextView prOwnerTV;
    public TextView prOwnerTypeTV;

    public PullRequestHolder(View itemView) {

        super(itemView);

        prTitleTV     = itemView.findViewById(R.id.pr_name_text);
        prBodyTV      = itemView.findViewById(R.id.pr_description_text);
        prOwnerTV     = itemView.findViewById(R.id.pr_owner_text);
        prOwnerTypeTV = itemView.findViewById(R.id.pr_owner_nick_text);

    }
}
