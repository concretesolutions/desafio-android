package com.example.lucas.concrete_solutions_desafio.model;


import java.util.ArrayList;
import java.util.List;

public class PullRequestList {
    private ArrayList<PullRequest> pullRequests = new ArrayList<>();

    public PullRequestList() {

    }

    public int pullRequestsCount(){
        return pullRequests.size();
    }

    public PullRequest getPullRequestByPosition(int position){
        return pullRequests.get(position);
    }

    public List<PullRequest> getPullRequests() {
        return pullRequests;
    }

    public void setPullRequests(ArrayList<PullRequest> repositories) {
        this.pullRequests = repositories;
    }

    public void setPullRequests(PullRequestList pullRequests) {
        int count = pullRequests.pullRequestsCount();


        for (int i=0;i<count; i++){
            this.pullRequests.add(pullRequests.getPullRequestByPosition(i));
        }
    }

    public void setPullRequests(PullRequest pullRequest) {
        this.pullRequests.add(pullRequest);
    }

    public void clear() {
        pullRequests.clear();
    }

}
