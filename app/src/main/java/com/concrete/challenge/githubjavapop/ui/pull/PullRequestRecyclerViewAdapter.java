package com.concrete.challenge.githubjavapop.ui.pull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.concrete.challenge.githubjavapop.R;
import com.concrete.challenge.githubjavapop.domain.PullRequest;

import java.util.ArrayList;

public class PullRequestRecyclerViewAdapter extends RecyclerView.Adapter<PullRequestRecyclerViewAdapter.ViewHolder> {

    private ArrayList<PullRequest> data;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    public PullRequestRecyclerViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pull_request_card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(getAdapterPosition());
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
