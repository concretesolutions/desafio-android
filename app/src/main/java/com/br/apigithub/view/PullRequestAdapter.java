package com.br.apigithub.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.br.apigithub.R;
import com.br.apigithub.beans.Pull;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rlima on 04/10/18.
 */

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder> {
    private List<Pull> list;
    private Context mContext;
    private ItemClickListener itemClickListener;

    public PullRequestAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public PullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_pull, parent, false);
        return new PullRequestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PullRequestViewHolder holder, int position) {
        Pull pr = list.get(position);
        holder.tvTitle.setText(pr.getTitle());
        holder.tvUsername.setText(pr.getUser().getName());
        holder.tvDescription.setText(pr.getDescription());
        if (pr.getUser().getAvatarUrl() != null && !pr.getUser().getAvatarUrl().isEmpty()) {
            Glide.with(mContext).load(pr.getUser().getAvatarUrl()).into(holder.ivUser);
            holder.ivUser.setBackgroundColor(ContextCompat.getColor(mContext, R.color.branco));
        }
    }

    @Override
    public int getItemCount() {
        return list != null && !list.isEmpty() ? list.size() : 0;
    }

    public void setPullRequests(List<Pull> list) {
        if (this.list != null) {
            this.list.addAll(list);
        } else {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClick(String url);
    }

    class PullRequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_titulo)
        TextView tvTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.iv_user)
        CircleImageView ivUser;
        @BindView(R.id.tv_username)
        TextView tvUsername;

        public PullRequestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Pull pull = list.get(adapterPosition);
            itemClickListener.onItemClick(pull.getHtmlUrl());
        }
    }

}
