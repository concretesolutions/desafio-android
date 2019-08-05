package com.paulobsa.desafioandroid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PullRequestSearchResult implements Serializable {
    private PullRequest[] pullRequests;

    public List<PullRequest> getPullRequests() {
        if (pullRequests == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(pullRequests));
    }

    public void setPullRequests(PullRequest[] pullRequests) {
        this.pullRequests = pullRequests;
    }

    public int getClosedPRCount() {
        if (getPullRequests() == null) return 0;

        String url = getPullRequests().get(0).getUrl();
        String[] strList = url.split("/");
        String totalPRs = strList[strList.length-1];

        return Integer.valueOf(totalPRs) - getPullRequests().size();
    }
}
