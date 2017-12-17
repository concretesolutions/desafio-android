package br.com.githubrepos.data.service;

import java.util.List;

import br.com.githubrepos.data.entity.PullRequest;

public interface PullRequestServiceApi {

    interface PullRequestCallback<T> {
        void onLoaded(T data);
    }

    void list(String ownerLogin, String repoName, PullRequestCallback<List<PullRequest>> callback);
}
