package com.github.api.morepopulargithubapp;

import com.github.api.morepopulargithubapp.model.vo.PullRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PullRequestMock {
    public static List<PullRequest> getPullRequestsMock(){

        List<PullRequest> pullRequests = new ArrayList<>();
        Random gerador = new Random();

        for (int i = 0; i<3; i++){
            int j =  gerador.nextInt();
            PullRequest pullRequest = new PullRequest();
            pullRequest.setId(j);
            pullRequest.setTitle("Tilte Pull Request");

            pullRequests.add(pullRequest);
        }

        return pullRequests;

    }

}
