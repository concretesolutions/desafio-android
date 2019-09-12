package com.feliperamoscarvalho.appconsultagithub;

import com.feliperamoscarvalho.appconsultagithub.data.RepositoryServiceAPI;
import com.feliperamoscarvalho.appconsultagithub.data.model.RepositorySearchResult;

public class FakeRepositoryServiceImpl implements RepositoryServiceAPI {

    private static final RepositorySearchResult REPOSITORIES_SERVICE_DATA = new RepositorySearchResult();


    @Override
    public void getRepositories(RepositoryServiceCallback<RepositorySearchResult> callback) {
        callback.onLoaded(REPOSITORIES_SERVICE_DATA);
    }
}
