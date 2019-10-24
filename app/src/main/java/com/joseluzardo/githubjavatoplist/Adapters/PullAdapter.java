package com.joseluzardo.githubjavatoplist.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joseluzardo.githubjavatoplist.Models.Pulls.PullsItem;
import com.joseluzardo.githubjavatoplist.R;
import com.joseluzardo.githubjavatoplist.Utils.RoundedCornersTransform;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PullAdapter extends RecyclerView.Adapter<PullAdapter.ViewHolder>{

    private List<PullsItem> items;
    private View.OnClickListener myClickListener;
    private SimpleDateFormat sdf;

    public PullAdapter(List<PullsItem> items, View.OnClickListener myClickListener) {
        this.items = items;
        this.myClickListener = myClickListener;
        this.sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }

    @Override
    public @NonNull PullAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pr, parent, false);

        return new PullAdapter.ViewHolder(view, myClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PullAdapter.ViewHolder holder, int position) {

        try {

        holder.ivTitle.setText(items.get(position).getTitle());
        holder.tvUsername.setText(items.get(position).getUser().getLogin());
        holder.tvBody.setText(items.get(position).getBody());
        holder.tvDate.setText(sdf.format(sdf.parse(items.get(position).getCreated_at())));
        Picasso.get()
                .load(items.get(position).getUser().getAvatarURL())
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
        TextView tvBody;
        TextView tvUsername;
        TextView tvDate;

        ViewHolder(View itemView, View.OnClickListener myClickListener) {
            super(itemView);

            itemView.setOnClickListener(myClickListener);
            ivPict = itemView.findViewById(R.id.ivPict);
            ivTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvDate = itemView.findViewById(R.id.tvDate);

        }
    }
}
