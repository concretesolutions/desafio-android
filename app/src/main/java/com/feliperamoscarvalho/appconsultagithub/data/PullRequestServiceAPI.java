package com.feliperamoscarvalho.appconsultagithub.data;

import com.feliperamoscarvalho.appconsultagithub.data.model.Pull;

import java.util.List;

public interface PullRequestServiceAPI {

    interface PullRequestServiceCallback<T> {

        void onLoaded(T pullRequests);
    }

    void getPullRequests(String login, String repository,
                         PullRequestServiceAPI.PullRequestServiceCallback<List<Pull>> callback);


}
