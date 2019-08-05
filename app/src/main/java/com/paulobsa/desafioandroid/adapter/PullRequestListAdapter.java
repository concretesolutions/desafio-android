package com.paulobsa.desafioandroid.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
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
    ViewGroup viewGroup;
    PullRequestListAdapterOnclickHandler mHandler;

    public PullRequestListAdapter(PullRequestListAdapterOnclickHandler mHandler, Context context) {
        this.mHandler = mHandler;
        this.context = context;
        pullRequestSearchResult = new PullRequestSearchResult();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.viewGroup = viewGroup;
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

    public interface PullRequestListAdapterOnclickHandler {
        void onCardClick(String url);
    }

    public class ViewHolder extends BaseViewHolder  {
        CardView mCard;
        private TextView textViewRepoName;
        private TextView textViewPRDescription;
        private TextView textViewUsername;
        private TextView textViewFullname;
        private TextView textViewOpenPRCount;
        private TextView textViewClosedPRCount;
        private ImageView imageUser;

        public ViewHolder(View itemView) {
            super(itemView);
            mCard = itemView.findViewById(R.id.pull_request_info_card);
            textViewRepoName = itemView.findViewById(R.id.textViewRepoName);
            textViewPRDescription = itemView.findViewById(R.id.textViewPRDescription);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewFullname = itemView.findViewById(R.id.textViewFullname);
            imageUser = itemView.findViewById(R.id.imageUser);

            textViewOpenPRCount = ((ConstraintLayout)viewGroup.getParent()).findViewById(R.id.textViewOpenPRCount);
            textViewClosedPRCount = ((ConstraintLayout)viewGroup.getParent()).findViewById(R.id.textViewClosedPRCount);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final PullRequest pullRequest = pullRequestSearchResult.getPullRequests().get(position);

            textViewRepoName.setText(pullRequest.getTitle());
            textViewPRDescription.setText(pullRequest.getBody());
            textViewUsername.setText(pullRequest.getUser().getLogin());
            textViewFullname.setText(pullRequest.getUser().getLogin());
            textViewOpenPRCount.setText(String.valueOf(pullRequestSearchResult.getPullRequests().size()));
            textViewClosedPRCount.setText(String.valueOf(pullRequestSearchResult.getClosedPRCount()));
            Glide.with(context).load(pullRequest.getUser().getAvatarUrl()).into(imageUser);

            mCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHandler.onCardClick(pullRequest.getHtmlUrl());
                }
            });
        }
    }
}
