package br.com.githubrepos.data.service.impl;

import java.util.List;

import br.com.githubrepos.data.entity.PullRequest;
import br.com.githubrepos.data.service.PullRequestServiceApi;

public class PullRequestServiceApiImpl implements PullRequestServiceApi {

    @Override
    public void list(String ownerLogin, String repoName,
                     PullRequestCallback<List<PullRequest>> callback) {
    }
}
