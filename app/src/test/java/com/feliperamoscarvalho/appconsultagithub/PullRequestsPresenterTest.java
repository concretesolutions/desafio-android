package com.feliperamoscarvalho.appconsultagithub;

import com.feliperamoscarvalho.appconsultagithub.data.PullRequestServiceAPI;
import com.feliperamoscarvalho.appconsultagithub.data.model.Pull;
import com.feliperamoscarvalho.appconsultagithub.data.model.User;
import com.feliperamoscarvalho.appconsultagithub.pullrequests.PullRequestsContract;
import com.feliperamoscarvalho.appconsultagithub.pullrequests.PullRequestsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class PullRequestsPresenterTest {

    private String LOGIN = "iluwatar";
    private String REPOSITORY = "java-design-patterns";

    private static ArrayList<User> users = new ArrayList<User>(){{
        add(new User(582346, "iluwatar", "https://avatars1.githubusercontent.com/u/582346?v=4"));
        add(new User(6764390, "elastic", "https://avatars0.githubusercontent.com/u/6764390?v=4"));
        add(new User(6407041, "ReactiveX", "https://avatars1.githubusercontent.com/u/6407041?v=4"));
    }};

    private static ArrayList<Pull> pulls = new ArrayList<Pull>(){{
        add(new Pull(301690415, "https://github.com/iluwatar/java-design-patterns/pull/910",
                users.get(0), "Use Functional style code [abstract-document]",
                "Use functional style code and direct `foreach` instead of `entrySet` one.",
                "2019-07-26T21:26:25Z"));
        add(new Pull(299192081, "https://github.com/iluwatar/java-design-patterns/pull/901",
                users.get(1), "#895 MInor bug fix -> Code comment change (https://github.com/i…",
                "Code comment change.\\r\\nHayes modem with Unix configurator to Hayes modem with Dos configurator",
                "2019-07-19T05:41:51Z"));
        add(new Pull(286461455, "https://github.com/iluwatar/java-design-patterns/pull/884",
                users.get(2), "add build-md.sh to generate markdown doc",
                "Pull request title\\r\\n\\r\\n- Clearly and concisely describes what it does",
                "2019-06-09T10:16:56Z"));
    }};

    @Mock
    private FakePullRequestServiceImpl mApi;

    @Mock
    private PullRequestsContract.View mPullRequestsView;

    @Captor
    private ArgumentCaptor<PullRequestServiceAPI.PullRequestServiceCallback> mLoadPullsCallbackCaptor;

    private PullRequestsPresenter mPullRequestsPresenter;

    @Before
    public void setupPullRequestsPresenter() {

        MockitoAnnotations.initMocks(this);
        mPullRequestsPresenter = new PullRequestsPresenter(mApi, mPullRequestsView);

    }

    @Test
    public void loadRepositoriesFromApiAndFillScreen() {

        mPullRequestsPresenter.loadPullRequest(LOGIN, REPOSITORY);

        verify(mApi).getPullRequests(eq(LOGIN), eq(REPOSITORY), mLoadPullsCallbackCaptor.capture());
        mLoadPullsCallbackCaptor.getValue().onLoaded(pulls);

        verify(mPullRequestsView).setLoading(false);

    }
}
