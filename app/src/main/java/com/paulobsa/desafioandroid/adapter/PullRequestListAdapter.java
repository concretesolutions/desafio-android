package com.paulobsa.desafioandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paulobsa.desafioandroid.R;
import com.paulobsa.desafioandroid.model.PullRequest;
import com.paulobsa.desafioandroid.model.PullRequestSearchResult;
import com.paulobsa.desafioandroid.view.BaseViewHolder;

public class PullRequestListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    PullRequestSearchResult pullRequestSearchResult;
    Context context;

    public PullRequestListAdapter(Context context) {
        this.context = context;
        pullRequestSearchResult = new PullRequestSearchResult();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PullRequestListAdapter.ViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pull_request_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int position) {
        baseViewHolder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return pullRequestSearchResult == null
                || pullRequestSearchResult.getPullRequests() == null ? 0 : pullRequestSearchResult.getPullRequests().size();

    }

    public PullRequestSearchResult getSearchResult() {
        return pullRequestSearchResult;
    }

    public void addResult(PullRequestSearchResult pullRequestSearchResult) {
        this.pullRequestSearchResult = pullRequestSearchResult;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder  {
        private TextView textViewRepoName;
        private TextView textViewPRDescription;
        private TextView textViewUsername;
        private TextView textViewFullname;
        private ImageView imageUser;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewRepoName = itemView.findViewById(R.id.textViewRepoName);
            textViewPRDescription = itemView.findViewById(R.id.textViewPRDescription);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewFullname = itemView.findViewById(R.id.textViewFullname);
            imageUser = itemView.findViewById(R.id.imageUser);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            PullRequest pullRequest = pullRequestSearchResult.getPullRequests().get(position);

            textViewRepoName.setText(pullRequest.getTitle());
            textViewPRDescription.setText(pullRequest.getBody());
            textViewUsername.setText(pullRequest.getUser().getLogin());
            textViewFullname.setText(pullRequest.getUser().getLogin());
            Glide.with(context).load(pullRequest.getUser().getAvatarUrl()).into(imageUser);
        }
    }
}
