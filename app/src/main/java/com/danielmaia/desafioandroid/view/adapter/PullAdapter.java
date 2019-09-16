package com.danielmaia.desafioandroid.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.danielmaia.desafioandroid.App;
import com.danielmaia.desafioandroid.R;
import com.danielmaia.desafioandroid.model.Pull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PullAdapter extends RecyclerView.Adapter<PullAdapter.ViewHolder> {

    private PullAdapter.PullAdapterListener listener;
    private List<Pull> pullList;

    public interface PullAdapterListener {
        void onItemClick(String url);
    }

    public PullAdapter(PullAdapter.PullAdapterListener l, List<Pull> r) {
        listener = l;
        pullList = r;
    }

    @Override
    public PullAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PullAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pull, parent, false), listener);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(PullAdapter.ViewHolder holder, int position) {
        final Pull pull = pullList.get(position);

        holder.txtTitle.setText(pull.getTitle());
        holder.txtDesc.setText(pull.getBody());
        holder.txtDesc.setVisibility(pull.getBody().length() > 0 ? View.VISIBLE : View.GONE);
        holder.txtUsername.setText(pull.getOwner());

        Glide.with(App.getInstance())
                .load(pull.getAvatar())
                .placeholder(App.getInstance().getResources().getDrawable(R.drawable.ic_profile))
                .into(holder.imgProfile);

        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(pull.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return pullList.size();
    }

    public void setList(List<Pull> list) {
        pullList = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout llContainer;
        public TextView txtTitle, txtDesc, txtUsername, txtName;
        public CircleImageView imgProfile;
        public PullAdapter.PullAdapterListener listener;

        public ViewHolder(View itemView, final PullAdapter.PullAdapterListener listener) {
            super(itemView);
            this.listener = listener;

            llContainer = itemView.findViewById(R.id.llContainer);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }

}




