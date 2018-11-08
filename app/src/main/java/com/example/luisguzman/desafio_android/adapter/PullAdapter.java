package com.example.luisguzman.desafio_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.luisguzman.desafio_android.R;
import com.example.luisguzman.desafio_android.helper.Utils;
import com.example.luisguzman.desafio_android.modal.DataListPull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PullAdapter extends RecyclerView.Adapter<PullAdapter.PullReqViewHolder> {

    private List<DataListPull> pullsList;
    private Context context;

    public PullAdapter(List<DataListPull> repositoriesList, Context context) {
        this.pullsList = repositoriesList;
        this.context = context;
    }


    @NonNull
    @Override
    public PullReqViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_pull, viewGroup, false);
        return new PullAdapter.PullReqViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PullReqViewHolder pullReqViewHolder, int i) {
        final DataListPull repository = pullsList.get(i);

        pullReqViewHolder.pullName.setText(repository.getName());
        pullReqViewHolder.pullDescription.setText(Utils.ellipsis(repository.getDescription(), 100).replace("\n", ""));
        pullReqViewHolder.pullUser.setText(repository.getUser().getUser());
        Glide.with(context)
                .load(repository.getUser().getAvatar())
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(pullReqViewHolder.pullAvatar);

        pullReqViewHolder.pullLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(repository.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pullsList.size();
    }


    public class PullReqViewHolder extends RecyclerView.ViewHolder {

        public TextView pullName;
        public TextView pullDescription;
        public TextView pullUser;
        public CircleImageView pullAvatar;
        public LinearLayout pullLayout;


        public PullReqViewHolder(@NonNull View itemView) {
            super(itemView);
            pullName = (TextView) itemView.findViewById(R.id.name);
            pullDescription = (TextView) itemView.findViewById(R.id.description);
            pullUser = (TextView) itemView.findViewById(R.id.user_name);
            pullAvatar = (CircleImageView) itemView.findViewById(R.id.user_image);
            pullLayout = (LinearLayout) itemView.findViewById(R.id.pull_layout);

        }
    }
}
