package com.feliperamoscarvalho.appconsultagithub.data;

import com.feliperamoscarvalho.appconsultagithub.data.model.RepositorySearchResult;

public interface RepositoryServiceAPI {

    interface RepositoryServiceCallback<T> {

        void onLoaded(T repositories);
    }

    void getRepositories(RepositoryServiceCallback<RepositorySearchResult> callback);

}
