package com.example.brunovsiq.concreteapp.screens.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.brunovsiq.concreteapp.R;
import com.example.brunovsiq.concreteapp.models.Repository;
import com.example.brunovsiq.concreteapp.screens.MainActivity;
import com.example.brunovsiq.concreteapp.screens.pull_details.PullListActivity;
import com.makeramen.roundedimageview.RoundedImageView;

public class RepositoryViewHolder extends RecyclerView.ViewHolder {

    public TextView repoName;
    public TextView repoDescription;
    public TextView forkQuantity;
    public TextView starsQuantity;
    public RoundedImageView profilePicture;
    public TextView username;
    public TextView userFullname;

    private Repository repository;
    private Context context;

    public RepositoryViewHolder(View v) {
        super(v);

        repoName = v.findViewById(R.id.repository_name);
        repoDescription = v.findViewById(R.id.repository_desc);
        forkQuantity = v.findViewById(R.id.text_forks_quantity);
        starsQuantity = v.findViewById(R.id.text_stars_quantity);
        profilePicture = v.findViewById(R.id.image_username_picture);
        username = v.findViewById(R.id.text_username);
        userFullname = v.findViewById(R.id.text_fullname);

        this.context = v.getContext();

        v.setOnClickListener(repoClickListener);
    }

    public void setRepository(Repository repo) {
        this.repository = repo;
    }

    View.OnClickListener repoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intentToPull = new Intent(context, PullListActivity.class);
            intentToPull.putExtra("repo", repository);
            ( (MainActivity) context).startActivityForResult(intentToPull, Activity.RESULT_OK);
        }
    };

}
