package com.example.brunovsiq.concreteapp.screens;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.brunovsiq.concreteapp.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class RepositoryViewHolder extends RecyclerView.ViewHolder {

    public TextView repoName;
    public TextView repoDescription;
    public TextView forkQuantity;
    public TextView starsQuantity;
    public RoundedImageView profilePicture;
    public TextView username;
    public TextView userFullname;

    public RepositoryViewHolder(View v) {
        super(v);

        repoName = v.findViewById(R.id.repository_name);
        repoDescription = v.findViewById(R.id.repository_desc);
        forkQuantity = v.findViewById(R.id.text_forks_quantity);
        starsQuantity = v.findViewById(R.id.text_stars_quantity);
        profilePicture = v.findViewById(R.id.image_username_picture);
        username = v.findViewById(R.id.text_username);
        userFullname = v.findViewById(R.id.text_fullname);

        v.setOnClickListener(repoClickListener);
    }

    View.OnClickListener repoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {



        }
    };

}
