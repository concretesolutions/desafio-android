package com.feliperamoscarvalho.appconsultagithub;



import com.feliperamoscarvalho.appconsultagithub.data.RepositoryServiceAPI;
import com.feliperamoscarvalho.appconsultagithub.data.model.Item;
import com.feliperamoscarvalho.appconsultagithub.data.model.Owner;
import com.feliperamoscarvalho.appconsultagithub.data.model.RepositorySearchResult;
import com.feliperamoscarvalho.appconsultagithub.repositories.RepositoriesContract;
import com.feliperamoscarvalho.appconsultagithub.repositories.RepositoriesPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

public class RepositoriesPresenterTest {

    private static ArrayList<Owner> owners = new ArrayList<Owner>(){{
        add(new Owner(582346, "iluwatar", "https://avatars1.githubusercontent.com/u/582346?v=4"));
        add(new Owner(6764390, "elastic", "https://avatars0.githubusercontent.com/u/6764390?v=4"));
        add(new Owner(6407041, "ReactiveX", "https://avatars1.githubusercontent.com/u/6407041?v=4"));
    }};

    private static ArrayList<Item> items = new ArrayList<Item>(){{
        add(new Item(22790488, "java-design-pattern",
                "Design patterns implemented in Java", owners.get(0),
                50383, 16266));
        add(new Item(507775, "elasticsearch",
                "Open Source, Distributed, RESTful Search Engine", owners.get(1),
                43179, 14503));
        add(new Item(7508411, "ReactiveX/RxJava",
                "RxJava – Reactive Extensions for the JVM", owners.get(2),
                40026, 6740));
    }};

    private static RepositorySearchResult REPOSITORY_SEARCH_RESULT = new RepositorySearchResult(
            3, true, items
    );

    @Mock
    private FakeRepositoryServiceImpl mApi;

    @Mock
    private RepositoriesContract.View mRepositoriesView;

    @Captor
    private ArgumentCaptor<RepositoryServiceAPI.RepositoryServiceCallback> mLoadItemsCallbackCaptor;

    private RepositoriesPresenter mRepositoriesPresenter;

    @Before
    public void setupRepositoriesPresenter() {

        MockitoAnnotations.initMocks(this);
        mRepositoriesPresenter = new RepositoriesPresenter(mApi, mRepositoriesView);

    }

    @Test
    public void loadRepositoriesFromApiAndFillScreen() {

        mRepositoriesPresenter.loadRepository();

        verify(mApi).getRepositories(mLoadItemsCallbackCaptor.capture());
        mLoadItemsCallbackCaptor.getValue().onLoaded(REPOSITORY_SEARCH_RESULT);

        verify(mRepositoriesView).setLoading(false);
        verify(mRepositoriesView).showRepositoryItems(REPOSITORY_SEARCH_RESULT.getItems());

    }
}
