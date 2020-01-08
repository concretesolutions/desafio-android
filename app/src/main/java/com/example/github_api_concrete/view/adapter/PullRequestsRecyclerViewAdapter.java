package com.example.github_api_concrete.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github_api_concrete.R;
import com.example.github_api_concrete.model.pojo.repos.Item;
import com.example.github_api_concrete.view.interfaces.OnClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PullRequestsRecyclerViewAdapter extends RecyclerView.Adapter<PullRequestsRecyclerViewAdapter.ViewHolder> {

    private List<Item> pullRequestList;
    private OnClick listener;

    public PullRequestsRecyclerViewAdapter(List<Item> pullRequestList, OnClick listener){
        this.pullRequestList = pullRequestList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_pr, parent, false);
        return new PullRequestsRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item item = pullRequestList.get(position);
        holder.onBind(item);
        holder.itemView.setOnClickListener(v -> listener.click(item));
    }

    @Override
    public int getItemCount() {
        return pullRequestList.size();
    }

    public void updateList(List<Item> newPRList) {
        this.pullRequestList.clear();
        this.pullRequestList = newPRList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView userIcon;
        private TextView namePR;
        private TextView descriptionPR;
        private TextView datePR;
        private TextView username;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userIcon = itemView.findViewById(R.id.usericon2);
            namePR = itemView.findViewById(R.id.name2);
            descriptionPR = itemView.findViewById(R.id.description2);
            datePR = itemView.findViewById(R.id.creation_date);
            username = itemView.findViewById(R.id.username2);
        }

        public void onBind(Item item) {
            Picasso.get().load(item.getOwner().getAvatarUrl()).into(userIcon);
            namePR.setText(item.getFullName());
            descriptionPR.setText(item.getFullName());
            datePR.setText(item.getFullName());
            username.setText(item.getOwner().getLogin());
        }
    }
}
