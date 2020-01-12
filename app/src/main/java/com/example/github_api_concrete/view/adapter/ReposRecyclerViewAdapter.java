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

public class ReposRecyclerViewAdapter extends RecyclerView.Adapter<ReposRecyclerViewAdapter.ViewHolder> {

    private List<Item> itemList;
    private OnClick listener;

    public ReposRecyclerViewAdapter(List<Item> itemList, OnClick listener){
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_repos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item item = itemList.get(position);
        holder.onBind(item);
        holder.itemView.setOnClickListener(v -> listener.click(item));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateList(List<Item> newList){
        if (this.itemList.isEmpty()) {
            this.itemList = newList;
        } else {
            this.itemList.addAll(newList);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView userIcon;
        private TextView nameRepo;
        private TextView descriptionRepo;
        private TextView totalForks;
        private TextView totalStars;
        private TextView username;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userIcon = itemView.findViewById(R.id.usericon);
            nameRepo = itemView.findViewById(R.id.name);
            descriptionRepo = itemView.findViewById(R.id.description);
            totalForks = itemView.findViewById(R.id.number_of_forks);
            totalStars = itemView.findViewById(R.id.number_of_stars);
            username = itemView.findViewById(R.id.username);
        }

        public void onBind(Item item){
            Picasso.get()
                    .load(item.getOwner().getAvatarUrl())
                    .placeholder(R.drawable.usericon)
                    .into(userIcon);

            nameRepo.setText(item.getName());
            descriptionRepo.setText(item.getDescription());
            totalForks.setText(String.valueOf(item.getForksCount()));
            totalStars.setText(String.valueOf(item.getStargazersCount()));
            username.setText(item.getOwner().getLogin());
        }
    }
}
