package com.example.javapop.Events;

import com.example.javapop.Models.PullRequestList;

import java.util.List;

public class PullRequestEvent {
    private List<PullRequestList> pullRequests;

    public PullRequestEvent(List<PullRequestList> pullRequest) {
        this.pullRequests = pullRequest;
    }

    public List<PullRequestList> getPullRequests() {
        return pullRequests;
    }
}
