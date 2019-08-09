package com.postulacion.githubjavapop.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.postulacion.githubjavapop.R;
import com.postulacion.githubjavapop.model.User;
import com.postulacion.githubjavapop.model.PullRequest;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PullRequestRecyclerViewAdapter extends RecyclerView.Adapter<PullRequestRecyclerViewAdapter.ViewHolder> {

    private List<PullRequest> pullRequestList;
    private Context context;

    public PullRequestRecyclerViewAdapter(List<PullRequest> pullRequestList, Context context) {
        this.pullRequestList = pullRequestList;
        this.context = context;
    }

    @NonNull
    @Override
    public PullRequestRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pull_request, parent, false);
        return new PullRequestRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        PullRequest pullRequest = pullRequestList.get(position);
        User user = pullRequest.getUser();

        viewHolder.pullRequestTitle.setText(pullRequest.getTitle());
        viewHolder.pullRequestDescription.setText(pullRequest.getDescription());
        viewHolder.pullRequestUserName.setText(user.getUserName());
        viewHolder.pullRequestUserNickName.setText(user.getNickName());
        Picasso.with(context).load(pullRequestList.get(position).getUser().getAvatarUrl()).into(viewHolder.pullRequestUserImage);
    }

    @Override
    public int getItemCount() {
        return pullRequestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView pullRequestTitle;
        private TextView pullRequestDescription;
        private TextView pullRequestUserName;
        private TextView pullRequestUserNickName;
        private ImageView pullRequestUserImage;

        public ViewHolder(View view) {
            super(view);
            pullRequestTitle = view.findViewById(R.id.pull_title);
            pullRequestDescription = view.findViewById(R.id.pull_description);
            pullRequestUserName = view.findViewById(R.id.pull_user_name);
            pullRequestUserNickName = view.findViewById(R.id.pull_user_nickname);
            pullRequestUserImage = view.findViewById(R.id.pull_user_image);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            PullRequest pullRequest = pullRequestList.get(getAdapterPosition());
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pullRequest.getHtmlUrl()));
            context.startActivity(browserIntent);
        }
    }
}
