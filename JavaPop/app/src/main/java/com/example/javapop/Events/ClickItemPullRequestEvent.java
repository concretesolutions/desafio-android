package com.example.javapop.Events;

import com.example.javapop.Models.PullRequest;

public class ClickItemPullRequestEvent {
    private PullRequest mPullRequest;
    private int position;

    public ClickItemPullRequestEvent(PullRequest pullRequest, int adapterPosition) {
        this.mPullRequest = pullRequest;
        this.position = adapterPosition;
    }

    public PullRequest getPullRequest() {
        return mPullRequest;
    }

    public int getPosition() {
        return position;
    }
}
