package com.feliperamoscarvalho.appconsultagithub;

import com.feliperamoscarvalho.appconsultagithub.data.PullRequestServiceAPI;
import com.feliperamoscarvalho.appconsultagithub.data.model.Pull;

import java.util.ArrayList;
import java.util.List;

public class FakePullRequestServiceImpl implements PullRequestServiceAPI {

    private static final List<Pull> PULL_REQUESTS_SERVICE_DATA = new ArrayList<>();


    @Override
    public void getPullRequests(String login, String repository,
                                PullRequestServiceCallback<List<Pull>> callback) {
        callback.onLoaded(PULL_REQUESTS_SERVICE_DATA);
    }
}
