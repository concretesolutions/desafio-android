package com.marcos.desafioandroidconcrete.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.marcos.desafioandroidconcrete.R;
import com.marcos.desafioandroidconcrete.domain.PullRequest;
import com.marcos.desafioandroidconcrete.util.ItemClickListener;
import com.marcos.desafioandroidconcrete.util.Methods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 16,Abril,2020
 */
public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.MyViewHolder> {


    private List<PullRequest> list = new ArrayList<>();
    private static ItemClickListener itemClickListener;

    public PullRequestAdapter(List<PullRequest> list) {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_pull_request, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PullRequest pullRequest = list.get(position);
        if (pullRequest != null) {
            holder.titlePullRequest.setText(pullRequest.getTitle());
            holder.bodyPullRequest.setText(pullRequest.getBody());

            holder.username.setText(pullRequest.getUser().getLogin());
            Picasso.get().load(pullRequest.getUser().getAvatarUrl()
            ).noFade().fit().error(R.drawable.ic_avatar).into(holder.image);

            holder.created.setText(Methods.formatDate(pullRequest.getCreated()));

        }
    }


    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titlePullRequest, bodyPullRequest, created, username;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            titlePullRequest = view.findViewById(R.id.tv_title_pull_request);
            bodyPullRequest = view.findViewById(R.id.tv_body_pull_request);
            created = view.findViewById(R.id.tv_created);
            username = view.findViewById(R.id.tv_username);
            image = view.findViewById(R.id.iv_avatar);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition());
            }

        }
    }
}