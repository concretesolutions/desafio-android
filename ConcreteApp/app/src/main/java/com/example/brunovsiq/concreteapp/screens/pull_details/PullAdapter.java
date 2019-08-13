package com.example.brunovsiq.concreteapp.screens.pull_details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brunovsiq.concreteapp.R;
import com.example.brunovsiq.concreteapp.models.Pull;
import com.example.brunovsiq.concreteapp.models.Repository;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PullAdapter extends RecyclerView.Adapter {

    private ArrayList<Pull> pullArrayList;
    private Context context;
    private Pull pull;

    PullViewholder pullViewholder;

    public PullAdapter(ArrayList<Pull> events, Context context){
        this.pullArrayList = events;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pull_item, parent, false);
        return new PullViewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        pullViewholder = (PullViewholder) holder;
        pull = pullArrayList.get(position);

        Picasso.get().load(pull.getProfilePictureUrl()).into(pullViewholder.profilePicture);

        pullViewholder.fullname.setText(pull.getPullTitle());
        pullViewholder.username.setText(pull.getUsername());
        pullViewholder.fullname.setText(pull.getFullname());
        pullViewholder.pullBody.setText(pull.getPullBody());
        pullViewholder.pullTitle.setText(pull.getPullTitle());

    }

    @Override
    public int getItemCount() {
        return pullArrayList.size();
    }

    private class PullViewholder extends RecyclerView.ViewHolder {

        private TextView pullTitle;
        private TextView pullBody;
        private TextView username;
        private TextView fullname;
        private RoundedImageView profilePicture;

        public PullViewholder(@NonNull View v) {
            super(v);

            pullTitle = v.findViewById(R.id.pull_title);
            pullBody = v.findViewById(R.id.pull_body);
            username = v.findViewById(R.id.pull_username);
            fullname = v.findViewById(R.id.pull_fullname);
            profilePicture = v.findViewById(R.id.user_picture);

            v.setOnClickListener(itemClickListener);
        }

        View.OnClickListener itemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(pullArrayList.get(getAdapterPosition()).getUrl()));
                context.startActivity(i);
            }
        };
    }
}
