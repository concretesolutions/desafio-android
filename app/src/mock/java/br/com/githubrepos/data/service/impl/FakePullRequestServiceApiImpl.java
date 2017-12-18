package br.com.githubrepos.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.githubrepos.data.entity.PullRequest;
import br.com.githubrepos.data.entity.User;
import br.com.githubrepos.data.service.PullRequestServiceApi;

public class FakePullRequestServiceApiImpl implements PullRequestServiceApi {

    private static final List<PullRequest> PULL_REQUEST_LIST;

    static {
        PULL_REQUEST_LIST = new ArrayList<PullRequest>();

        for (int i = 0; i < 15; i++) {
            User user = new User(i + 100, "userLogin" + i, "https://avatars2.githubusercontent.com/u/6407041?v=3",
                    "https://api.github.com/users/ReactiveX");

            PullRequest pullRequest = new PullRequest(i * (i + 1), "Further refactor and extend testing",
                    "This moves updateReplicaRequest to createPrimaryResponse",
                    "https://github.com/elastic/elasticsearch/pull/23665",
                    "open", user);

            PULL_REQUEST_LIST.add(pullRequest);
        }
    }

    @Override
    public void list(String ownerLogin, String repoName, PullRequestCallback<List<PullRequest>> callback) {
        callback.onLoaded(PULL_REQUEST_LIST);
    }

}
