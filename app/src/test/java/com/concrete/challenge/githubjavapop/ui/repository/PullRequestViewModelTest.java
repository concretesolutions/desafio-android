package com.concrete.challenge.githubjavapop.ui.repository;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;

import com.concrete.challenge.githubjavapop.api.Api;
import com.concrete.challenge.githubjavapop.api.SingleSchedulers;
import com.concrete.challenge.githubjavapop.domain.PullRequest;
import com.concrete.challenge.githubjavapop.ui.pull.PullRequestViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Single;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PullRequestViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    Api api;
    PullRequestViewModel pullRequestViewModel;
    @Mock
    Observer<ArrayList<PullRequest>> pullRequestObserver;
    @Mock
    Observer<Throwable> errorObserver;
    @Mock
    LifecycleOwner lifecycleOwner;
    Lifecycle lifecycle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        pullRequestViewModel = new PullRequestViewModel(api, SingleSchedulers.TEST);
        pullRequestViewModel.getPullRequests().observeForever(pullRequestObserver);
        pullRequestViewModel.getError().observeForever(errorObserver);
    }

    @Test
    public void testObserverNull() {
        when(api.getPullRequests("", "", 1)).thenReturn(null);
        assertNotNull(pullRequestViewModel.getPullRequests());
        assertTrue(pullRequestViewModel.getPullRequests().hasObservers());
    }

    @Test
    public void testApiLoadSuccess() {
        ArrayList<PullRequest> pullRequests = new ArrayList<>();
        pullRequests.add(new PullRequest());

        when(api.getPullRequests("", "", 1)).thenReturn(Single.just(pullRequests));
        pullRequestViewModel.loadPullRequests("", "", 1);
        verify(pullRequestObserver).onChanged(pullRequests);
    }

    @Test
    public void testApiLoadFailed() {
        Throwable throwable = new Throwable();

        when(api.getPullRequests("", "", 1)).thenReturn(Single.error(throwable));
        pullRequestViewModel.loadPullRequests("", "", 1);
        verify(errorObserver).onChanged(throwable);
    }

    @After
    public void destroy() {
        api = null;
        pullRequestViewModel = null;
    }
}
