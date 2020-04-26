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
import com.concrete.challenge.githubjavapop.domain.User;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        PullRequest pullRequest = data.get(position);
        User user = pullRequest.user;

        if(user != null) {
            Picasso.get().load(user.avatarUrl).into(holder.userImage);
            holder.userName.setText(user.login);
        }

        if(pullRequest != null) {
            holder.pullRequestTitle.setText(pullRequest.title);

            if(pullRequest.body != null && !pullRequest.body.equals("")) {
                holder.pullRequestBody.setText(pullRequest.body);
                holder.pullRequestBody.setVisibility(View.VISIBLE);
            } else {
                holder.pullRequestBody.setVisibility(View.GONE);
            }

            if(pullRequest.createdAt != null) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String releasedText = df.format(pullRequest.createdAt);
                holder.createdAt.setText(releasedText);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setPullRequests(@NonNull ArrayList<PullRequest> pullRequests) {
        data = pullRequests;
        notifyDataSetChanged();
    }

    public ArrayList<PullRequest> getPullRequests() {
        return data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView pullRequestTitle;
        public TextView pullRequestBody;
        public TextView userName;
        public TextView createdAt;
        public ImageView userImage;

        ViewHolder(View itemView) {
            super(itemView);
            pullRequestTitle = itemView.findViewById(R.id.pull_request_title);
            pullRequestBody = itemView.findViewById(R.id.pull_request_body);
            userName = itemView.findViewById(R.id.username);
            createdAt = itemView.findViewById(R.id.created_at);
            userImage = itemView.findViewById(R.id.user_image);
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
