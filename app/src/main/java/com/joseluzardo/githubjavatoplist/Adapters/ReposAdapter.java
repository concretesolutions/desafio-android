package com.joseluzardo.githubjavatoplist.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joseluzardo.githubjavatoplist.Models.Repos.ItemRepos;
import com.joseluzardo.githubjavatoplist.R;
import com.joseluzardo.githubjavatoplist.Utils.RoundedCornersTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder>{

    private List<ItemRepos> items;
    private View.OnClickListener myClickListener;
    private String viewLayout;

    public ReposAdapter(List<ItemRepos> items, View.OnClickListener myClickListener, String viewLayout) {
        this.items = items;
        this.myClickListener = myClickListener;
        this.viewLayout = viewLayout;
    }

    @Override
    public @NonNull ReposAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;

        if (viewLayout.equals("list")){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repos, parent, false);
        }else if (viewLayout.equals("grid")){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repos_grid, parent, false);
        }

        return new ReposAdapter.ViewHolder(view, myClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposAdapter.ViewHolder holder, int position) {

        try {

            holder.ivTitle.setText(items.get(position).getName());
            holder.tvDescription.setText(items.get(position).getDescription());
            holder.tvUsername.setText(items.get(position).getOwner().getLogin());
            holder.tvFork.setText(String.valueOf(items.get(position).getForks()));
            holder.tvStars.setText(String.valueOf(items.get(position).getStargazers_count()));
            Picasso.get()
                    .load(items.get(position).getOwner().getAvatar_url())
                    .transform(new RoundedCornersTransform())
                    .into(holder.ivPict);

        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.itemView.setTag(String.valueOf(position));

    }



    @Override
    public int getItemCount() {
        return items.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPict;
        TextView ivTitle;
        TextView tvDescription;
        TextView tvUsername;
        TextView tvFork;
        TextView tvStars;

        ViewHolder(View itemView, View.OnClickListener myClickListener) {
            super(itemView);

            itemView.setOnClickListener(myClickListener);
            ivPict = itemView.findViewById(R.id.ivPict);
            ivTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvFork = itemView.findViewById(R.id.tvFork);
            tvStars = itemView.findViewById(R.id.tvStars);

        }
    }
}
