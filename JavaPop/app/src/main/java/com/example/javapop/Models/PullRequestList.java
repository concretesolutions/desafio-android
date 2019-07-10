package com.example.javapop.Models;

import java.util.List;

public class PullRequestList extends PullRequest {

    private List<PullRequest> pullRequestList;

    public PullRequestList(List<PullRequest> pullRequestList) {
        this.pullRequestList = pullRequestList;
    }

    public List<PullRequest> getPullRequestList() {
        return pullRequestList;
    }
}
