package com.concrete.challenge.githubjavapop.ui.repository;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;

import com.concrete.challenge.githubjavapop.api.Api;
import com.concrete.challenge.githubjavapop.api.SingleSchedulers;
import com.concrete.challenge.githubjavapop.domain.RepositoriesResponse;
import com.concrete.challenge.githubjavapop.domain.Repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import io.reactivex.Single;

public class RepositoryViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    Api api;
    RepositoryViewModel repositoryViewModel;
    @Mock
    Observer<ArrayList<Repository>> repositoryObserver;
    @Mock
    Observer<Throwable> errorObserver;
    @Mock
    LifecycleOwner lifecycleOwner;
    Lifecycle lifecycle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        repositoryViewModel = new RepositoryViewModel(api, SingleSchedulers.TEST);
        repositoryViewModel.getRepositories().observeForever(repositoryObserver);
        repositoryViewModel.getError().observeForever(errorObserver);
    }

    @Test
    public void testObserverNull() {
        when(api.getRepositories(1)).thenReturn(null);
        assertNotNull(repositoryViewModel.getRepositories());
        assertTrue(repositoryViewModel.getRepositories().hasObservers());
    }

    @Test
    public void testApiLoadSuccess() {
        RepositoriesResponse repositoriesResponse = new RepositoriesResponse();
        repositoriesResponse.items = new ArrayList<>();
        repositoriesResponse.items.add(new Repository());

        when(api.getRepositories(1)).thenReturn(Single.just(repositoriesResponse));
        repositoryViewModel.loadRepositories(1);
        verify(repositoryObserver).onChanged(repositoriesResponse.items);
    }

    @Test
    public void testApiLoadFailed() {
        Throwable throwable = new Throwable();

        when(api.getRepositories(1)).thenReturn(Single.error(throwable));
        repositoryViewModel.loadRepositories(1);
        verify(errorObserver).onChanged(throwable);
    }

    @After
    public void destroy() {
        api = null;
        repositoryViewModel = null;
    }
}
