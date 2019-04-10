package com.example.sharked.accenture.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sharked.accenture.R;

public class RepositoryHolder extends RecyclerView.ViewHolder {
    public LinearLayout repositoryContainerLL;
    public TextView repositoryStarTV;
    public TextView repositoryPRTV;
    public TextView repositoryOwnerTV;
    public TextView repositoryOwnerNickTV;
    public TextView repositoryNameTV;
    public TextView repositoryDescriptionTV;

    public RepositoryHolder(View itemView) {

        super(itemView);
        repositoryContainerLL   = (LinearLayout) itemView.findViewById(R.id.li_container);
        repositoryNameTV        = (TextView)     itemView.findViewById(R.id.repository_name_text);
        repositoryDescriptionTV = (TextView)     itemView.findViewById(R.id.repository_description_text);
        repositoryStarTV        = (TextView)     itemView.findViewById(R.id.repository_star_text);
        repositoryPRTV          = (TextView)     itemView.findViewById(R.id.repository_pr_text);
        repositoryOwnerTV       = (TextView)     itemView.findViewById(R.id.repository_owner_text);
        repositoryOwnerNickTV   = (TextView)     itemView.findViewById(R.id.repository_owner_nick_text);

    }
}
