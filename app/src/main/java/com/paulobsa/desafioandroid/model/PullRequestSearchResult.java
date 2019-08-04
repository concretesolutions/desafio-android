package com.paulobsa.desafioandroid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PullRequestSearchResult implements Serializable {
    private PullRequest[] pullRequests;

    public List<PullRequest> getPullRequests() {
        if (pullRequests == null) {
            return null;
        }
        return new ArrayList<>(Arrays.asList(pullRequests));
    }

    public void setPullRequests(PullRequest[] pullRequests) {
        this.pullRequests = pullRequests;
    }
}
